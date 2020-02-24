package nl.rabobank.aggregator.service.impl;

import nl.rabobank.aggregator.model.domain.Account;
import nl.rabobank.aggregator.model.domain.PaymentCard;
import nl.rabobank.aggregator.model.domain.PaymentCardMeta;
import nl.rabobank.aggregator.model.domain.PowerOfAttorney;
import nl.rabobank.aggregator.model.dto.AccountDto;
import nl.rabobank.aggregator.model.dto.AggregatedDto;
import nl.rabobank.aggregator.model.dto.PaymentCardDto;
import nl.rabobank.aggregator.model.mapper.AccountMapper;
import nl.rabobank.aggregator.model.mapper.PaymentCardMapper;
import nl.rabobank.aggregator.service.AccountService;
import nl.rabobank.aggregator.service.AggregationService;
import nl.rabobank.aggregator.service.CardDetailService;
import nl.rabobank.aggregator.service.PowerOfAttorneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class AggregationServiceImpl implements AggregationService {

    private final Logger log = LoggerFactory.getLogger(AggregationServiceImpl.class);

    private final static int ACCEPTABLE_TIMEOUT = 5;

    private CardDetailService cardDetailService;
    private AccountService accountService;
    private PowerOfAttorneyService powerOfAttorneyService;


    @Autowired
    public AggregationServiceImpl(CardDetailService cardDetailService,
                                  AccountService accountService,
                                  PowerOfAttorneyService powerOfAttorneyService) {
        this.cardDetailService = cardDetailService;
        this.accountService = accountService;
        this.powerOfAttorneyService = powerOfAttorneyService;
    }

    @Override
    public AggregatedDto getAggregatedForId(final String id) {
        final Optional<PowerOfAttorney> optPowerOfAttorney = powerOfAttorneyService.getPowerOfAttorney(id);

        if (optPowerOfAttorney.isEmpty()) {
            return new AggregatedDto();
        }

        final PowerOfAttorney powerOfAttorney = optPowerOfAttorney.get();

        final List<CompletableFuture<Optional<? extends PaymentCard>>> cardDetailFutures = getCardDetails(powerOfAttorney);

        final CompletableFuture<Optional<Account>> accountFuture = getAccountDetails(powerOfAttorney);

        waitForAllFuturesToComplete(cardDetailFutures, accountFuture);

        return populateAggregatedData(powerOfAttorney, cardDetailFutures, accountFuture);

    }

    /**
     * Fetch account details.
     *
     * @param powerOfAttorney
     * @return
     */
    private CompletableFuture<Optional<Account>> getAccountDetails(final PowerOfAttorney powerOfAttorney) {
        return accountService.getAccount(powerOfAttorney.getAccountNumber());
    }

    /**
     * Fetch payment card details.
     *
     * @param powerOfAttorney
     * @return
     */
    private List<CompletableFuture<Optional<? extends PaymentCard>>> getCardDetails(final PowerOfAttorney powerOfAttorney) {
        final List<CompletableFuture<Optional<? extends PaymentCard>>> cardDetailFutures = new ArrayList<>();
        final List<PaymentCardMeta> paymentCardMetas = powerOfAttorney.getPaymentCardMetas();
        if (paymentCardMetas != null) {
            paymentCardMetas.forEach(paymentCardMeta -> {
                cardDetailFutures.add(cardDetailService.getCardDetails(paymentCardMeta, powerOfAttorney.getAuthorizations()));
            });
        }
        return cardDetailFutures;
    }

    /**
     * Populate final response object.
     *
     * @param attorney
     * @param cardDetailFutures
     * @param accountFuture
     * @return
     */
    public AggregatedDto populateAggregatedData(final PowerOfAttorney attorney,
                                                final List<CompletableFuture<Optional<? extends PaymentCard>>> cardDetailFutures,
                                                final CompletableFuture<Optional<Account>> accountFuture) {

        final AggregatedDto aggregatedDto = new AggregatedDto();
        aggregatedDto.setAccountId(attorney.getId());
        aggregatedDto.setAuthorizations(attorney.getAuthorizations());
        aggregatedDto.setDirection(attorney.getDirection());
        aggregatedDto.setGrantee(attorney.getGrantee());
        aggregatedDto.setGrantor(attorney.getGrantor());
        aggregatedDto.setAccount(getAccountData(accountFuture, attorney.getAccountNumber()));
        aggregatedDto.setCardList(getCardData(cardDetailFutures));
        return aggregatedDto;
    }

    /**
     * Get account details from future.
     *
     * @param accountFuture
     * @param accountNumber
     * @return
     */
    public AccountDto getAccountData(final CompletableFuture<Optional<Account>> accountFuture, final String accountNumber) {
        try {
            return AccountMapper.INSTANCE.map(accountFuture.get().orElse(new Account()), accountNumber);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Failed: {}", e);
        }
        return new AccountDto();
    }

    /**
     * Get paymentcard details from futures.
     *
     * @param cardDetailFutures
     * @return
     */
    public List<PaymentCardDto> getCardData(final List<CompletableFuture<Optional<? extends PaymentCard>>> cardDetailFutures) {
        final List<PaymentCard> cards = new ArrayList<>();
        cardDetailFutures.forEach(
                future -> {
                    try {
                        if (!future.isCompletedExceptionally()) {
                            future.get().ifPresent(cards::add);
                        } else {
                            log.error("Failed  future");
                        }
                    } catch (InterruptedException | ExecutionException e) {
                        log.error("Failed: {}", e);
                    }
                }
        );
        return PaymentCardMapper.INSTANCE.map(cards);
    }

    /**
     * Make sure all futures have completed.
     *
     * @param futures
     * @param future
     */
    private void waitForAllFuturesToComplete(final List<CompletableFuture<Optional<? extends PaymentCard>>> futures, final CompletableFuture<Optional<Account>> future) {
        try {
            final CompletableFuture[] futureArray = futures.toArray(new CompletableFuture[futures.size() + 1]);
            futureArray[futures.size()] = future;
            CompletableFuture.allOf(futureArray).get(ACCEPTABLE_TIMEOUT, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            log.error("Failed: {}", e);
        } catch (TimeoutException e) {
            log.error("Time-out: {}", e);
        }
    }

}
