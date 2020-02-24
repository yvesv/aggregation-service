package nl.rabobank.aggregator.model.domain;

public class DebitCard extends PaymentCard {

    private Limit atmLimit;
    private Limit posLimit;
    private boolean contactless;

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
