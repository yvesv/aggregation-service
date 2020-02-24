package nl.rabobank.aggregator.model.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PowerOfAttorney {

    @JsonProperty("id")
    private String id;

    @JsonProperty("grantor")
    private String grantor;

    @JsonProperty("grantee")
    private String grantee;

    @JsonProperty("account")
    private String accountNumber;

    @JsonProperty("direction")
    private Direction direction;

    @JsonProperty("cards")
    private List<PaymentCardMeta> paymentCardMetas;

    @JsonProperty("authorizations")
    private List<Authorization> authorizations;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrantor() {
        return grantor;
    }

    public void setGrantor(String grantor) {
        this.grantor = grantor;
    }

    public String getGrantee() {
        return grantee;
    }

    public void setGrantee(String grantee) {
        this.grantee = grantee;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<PaymentCardMeta> getPaymentCardMetas() {
        return paymentCardMetas;
    }

    public void setPaymentCardMetas(List<PaymentCardMeta> paymentCardMetas) {
        this.paymentCardMetas = paymentCardMetas;
    }

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
