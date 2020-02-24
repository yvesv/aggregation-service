package nl.rabobank.aggregator.service.impl;

import feign.FeignException;
import nl.rabobank.aggregator.client.Client;
import nl.rabobank.aggregator.model.domain.Authorization;
import nl.rabobank.aggregator.model.domain.PaymentCard;
import nl.rabobank.aggregator.model.domain.PaymentCardMeta;
import nl.rabobank.aggregator.model.domain.PaymentCardType;
import nl.rabobank.aggregator.service.CardDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class CardDetailServiceImpl implements CardDetailService {

    private final Logger log = LoggerFactory.getLogger(CardDetailServiceImpl.class);

    private Client client;

    @Autowired
    public CardDetailServiceImpl(final Client client) {
        this.client = client;
    }

    /**
     * Get payment card details for cardId is user is authorized. If not found or not authorized returns empty.
     */
    @Override
    @Async
    public CompletableFuture<Optional<? extends PaymentCard>> getCardDetails(final PaymentCardMeta paymentCardMeta, final List<Authorization> authorizations) {

        if (paymentCardMeta == null) {
            log.warn("CardId is null");
            return CompletableFuture.completedFuture(Optional.empty());
        }

        if (authorizations == null) {
            log.warn("Authorizations is null");
            return CompletableFuture.completedFuture(Optional.empty());
        }

        try {
            if (PaymentCardType.DEBIT_CARD.equals(paymentCardMeta.getType()) && authorizations.contains(Authorization.DEBIT_CARD)) {
                return CompletableFuture.completedFuture(client.getDebitCard(paymentCardMeta.getId()));
            }
            if (PaymentCardType.CREDIT_CARD.equals(paymentCardMeta.getType()) && authorizations.contains(Authorization.CREDIT_CARD)) {
                return CompletableFuture.completedFuture(client.getCreditCard(paymentCardMeta.getId()));
            }
        } catch (FeignException e) {
            log.warn("Exception fetching card details. CardId: {}, CardType: {}. Trace", paymentCardMeta.getId(), paymentCardMeta.getType(), e);
        }

        return CompletableFuture.completedFuture(Optional.empty());
    }

}
