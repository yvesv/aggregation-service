package nl.rabobank.aggregator.controller;

import nl.rabobank.aggregator.model.dto.AggregatedDto;
import nl.rabobank.aggregator.service.AggregationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AggregrationController {

    private final Logger log = LoggerFactory.getLogger(AggregrationController.class);

    private AggregationService aggregationService;

    @Autowired
    public AggregrationController(final AggregationService aggregationService) {
        this.aggregationService = aggregationService;
    }

    @GetMapping("/aggregated/{id}")
    public ResponseEntity<AggregatedDto> getAggregatedById(final @PathVariable("id") String id) {
        log.info("Rest request to fetch single attorney for id: {}", id);
        return ResponseEntity.ok(aggregationService.getAggregatedForId(id));
    }

}
