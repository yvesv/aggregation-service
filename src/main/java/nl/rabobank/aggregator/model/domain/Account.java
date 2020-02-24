package nl.rabobank.aggregator.model.domain;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Account {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("d-MM-yyyy");

    private String owner;
    private BigDecimal balance;
    private LocalDate created;
    private LocalDate ended;

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = LocalDate.parse(created, FORMATTER);
    }

    public LocalDate getEnded() {
        return ended;
    }

    public void setEnded(String ended) {
        this.ended = LocalDate.parse(ended, FORMATTER);
    }

}
