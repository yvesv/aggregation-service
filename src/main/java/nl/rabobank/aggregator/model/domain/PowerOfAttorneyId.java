package nl.rabobank.aggregator.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PowerOfAttorneyId {

    @JsonProperty("id")
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
