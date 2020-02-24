package nl.rabobank.aggregator.service;

import nl.rabobank.aggregator.model.dto.AggregatedDto;

public interface AggregationService {

    AggregatedDto getAggregatedForId(final String id);

}
