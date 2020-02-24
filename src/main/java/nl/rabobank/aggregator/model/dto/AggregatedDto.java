package nl.rabobank.aggregator.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import nl.rabobank.aggregator.model.domain.Authorization;
import nl.rabobank.aggregator.model.domain.Direction;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AggregatedDto {

    private String accountId;
    private List<PaymentCardDto> cardList;
    private String grantor;
    private String grantee;
    private AccountDto account;
    private Direction direction;
    private List<Authorization> authorizations;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public List<PaymentCardDto> getCardList() {
        return cardList;
    }

    public void setCardList(List<PaymentCardDto> cardList) {
        this.cardList = cardList;
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

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public List<Authorization> getAuthorizations() {
        return authorizations;
    }

    public void setAuthorizations(List<Authorization> authorizations) {
        this.authorizations = authorizations;
    }
}
