package nl.rabobank.aggregator.service.impl;

import feign.FeignException;
import nl.rabobank.aggregator.client.Client;
import nl.rabobank.aggregator.model.domain.PowerOfAttorney;
import nl.rabobank.aggregator.service.PowerOfAttorneyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PowerOfAttorneyServiceImpl implements PowerOfAttorneyService {

    private final Logger log = LoggerFactory.getLogger(PowerOfAttorneyServiceImpl.class);

    private Client client;

    @Autowired
    public PowerOfAttorneyServiceImpl(Client client) {
        this.client = client;
    }

    @Override
    public Optional<PowerOfAttorney> getPowerOfAttorney(final String id) {

        if (id == null) {
            log.warn("id is null");
            return Optional.empty();
        }

        try {
            return client.getPowerOfAttorney(id);
        } catch (FeignException e) {
            log.warn("Exception fetching power  of attorney for id: {}. Exception: {}", id, e);
            return Optional.empty();
        }
    }

}
