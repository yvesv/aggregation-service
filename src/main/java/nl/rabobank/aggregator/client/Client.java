package nl.rabobank.aggregator.client;

import nl.rabobank.aggregator.model.domain.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "attorneyClient", url = "http://localhost:8080")
public interface Client {

    @GetMapping("/power-of-attorneys")
    Optional<List<PowerOfAttorneyId>> getAllPowerOfAttorneyIds();

    @GetMapping("/power-of-attorneys/{id}")
    Optional<PowerOfAttorney> getPowerOfAttorney(@PathVariable("id") String id);

    @GetMapping("/debit-cards/{id}")
    Optional<DebitCard> getDebitCard(@PathVariable("id") String id);

    @GetMapping("/credit-cards/{id}")
    Optional<CreditCard> getCreditCard(@PathVariable("id") String id);

    @GetMapping("/accounts/{accountNumber}")
    Optional<Account> getAccount(@PathVariable("accountNumber") String accountNumber);

}
