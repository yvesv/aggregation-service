package nl.rabobank.aggregator.service;

import nl.rabobank.aggregator.model.domain.Authorization;
import nl.rabobank.aggregator.model.domain.PaymentCard;
import nl.rabobank.aggregator.model.domain.PaymentCardMeta;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public interface CardDetailService {

    CompletableFuture<Optional<? extends PaymentCard>> getCardDetails(final PaymentCardMeta paymentCardMeta, final List<Authorization> authorizations);

}
