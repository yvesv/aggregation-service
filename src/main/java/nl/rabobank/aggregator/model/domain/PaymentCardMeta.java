package nl.rabobank.aggregator.model.domain;

public class PaymentCardMeta {

    private String id;
    private PaymentCardType type;

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
}
