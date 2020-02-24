package nl.rabobank.aggregator.service;

import nl.rabobank.aggregator.model.domain.PowerOfAttorney;

import java.util.Optional;

public interface PowerOfAttorneyService {

    Optional<PowerOfAttorney> getPowerOfAttorney(final String id);

}
