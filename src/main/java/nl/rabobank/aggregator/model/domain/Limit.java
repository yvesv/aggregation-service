package nl.rabobank.aggregator.model.domain;

import java.math.BigDecimal;

public class Limit {

    private BigDecimal limit;
    private PeriodUnit periodUnit;

    public BigDecimal getLimit() {
        return limit;
    }

    public void setLimit(BigDecimal limit) {
        this.limit = limit;
    }

    public PeriodUnit getPeriodUnit() {
        return periodUnit;
    }

    public void setPeriodUnit(PeriodUnit periodUnit) {
        this.periodUnit = periodUnit;
    }
}
