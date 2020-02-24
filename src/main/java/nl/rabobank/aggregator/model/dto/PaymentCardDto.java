package nl.rabobank.aggregator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import nl.rabobank.aggregator.model.domain.Limit;
import nl.rabobank.aggregator.model.domain.PaymentCardType;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class PaymentCardDto {

    private String id;
    private PaymentCardType type;
    private int cardNumber;
    private int sequenceNumber;
    private String cardHolder;
    private BigDecimal monthlyLimit;
    private Limit atmLimit;
    private Limit posLimit;
    private boolean contactless;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PaymentCardType getType() {
        return type;
    }

    public void setType(PaymentCardType type) {
        this.type = type;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getCardHolder() {
        return cardHolder;
    }

    public void setCardHolder(String cardHolder) {
        this.cardHolder = cardHolder;
    }

    public BigDecimal getMonthlyLimit() {
        return monthlyLimit;
    }

    public void setMonthlyLimit(BigDecimal monthlyLimit) {
        this.monthlyLimit = monthlyLimit;
    }

    public Limit getAtmLimit() {
        return atmLimit;
    }

    public void setAtmLimit(Limit atmLimit) {
        this.atmLimit = atmLimit;
    }

    public Limit getPosLimit() {
        return posLimit;
    }

    public void setPosLimit(Limit posLimit) {
        this.posLimit = posLimit;
    }

    public boolean isContactless() {
        return contactless;
    }

    public void setContactless(boolean contactless) {
        this.contactless = contactless;
    }
}
