package nl.rabobank.aggregator.controller;


import nl.rabobank.aggregator.model.dto.AggregatedDto;
import nl.rabobank.aggregator.service.impl.AggregationServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Test example: production worthy code should contain many more tests (integration, unit etc).
 */
@WebMvcTest(AggregrationController.class)
public class AggregationControllerTest {

    @Autowired
    MockMvc mock;

    @MockBean
    AggregationServiceImpl aggregationService;

    @Test
    public void testController() throws Exception {
        Mockito.when(aggregationService.getAggregatedForId("0001")).thenReturn(getMockAttorneyDto());
        mock.perform(get("/aggregated/0001"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accountId").value("0001"));
    }


    private AggregatedDto getMockAttorneyDto() {
        AggregatedDto aggregatedDto = new AggregatedDto();
        aggregatedDto.setAccountId("0001");
        return aggregatedDto;
    }

}
