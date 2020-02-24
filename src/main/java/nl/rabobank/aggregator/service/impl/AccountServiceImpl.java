package nl.rabobank.aggregator.service.impl;

import feign.FeignException;
import nl.rabobank.aggregator.client.Client;
import nl.rabobank.aggregator.model.domain.Account;
import nl.rabobank.aggregator.model.domain.Iban;
import nl.rabobank.aggregator.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountServiceImpl implements AccountService {

    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);

    private Client client;

    public AccountServiceImpl(final Client client) {
        this.client = client;
    }

    @Override
    @Async
    public CompletableFuture<Optional<Account>> getAccount(final String accountNumber) {

        try {
            return CompletableFuture.completedFuture(client.getAccount(Iban.valueOf(accountNumber).getAccountNumber()));
        } catch (IllegalArgumentException e) {
            log.warn("Illegal value accountNumber: {}. Exception: {}", accountNumber, e);
        } catch (FeignException e) {
            log.warn("Exception fetching account details. Iban: {}. Exception: {}", accountNumber, e);
        }
        return CompletableFuture.completedFuture(Optional.empty());

    }

}
