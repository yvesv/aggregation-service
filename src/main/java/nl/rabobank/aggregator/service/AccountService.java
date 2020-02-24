package nl.rabobank.aggregator.service;

import nl.rabobank.aggregator.model.domain.Account;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface AccountService {

    CompletableFuture<Optional<Account>> getAccount(final String accountNumber);

}
