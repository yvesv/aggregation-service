package nl.rabobank.aggregator.model.domain;

import java.math.BigDecimal;

public class CreditCard extends PaymentCard {

    private BigDecimal monthlyLimit;

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }
}
