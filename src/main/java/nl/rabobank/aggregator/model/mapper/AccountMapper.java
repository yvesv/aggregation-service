package nl.rabobank.aggregator.model.mapper;

import nl.rabobank.aggregator.model.domain.Account;
import nl.rabobank.aggregator.model.dto.AccountDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    @Mappings({
            @Mapping(source = "account.owner", target = "owner"),
            @Mapping(source = "account.balance", target = "balance"),
            @Mapping(source = "account.created", target = "created"),
            @Mapping(source = "account.ended", target = "ended"),
            @Mapping(source = "accountNumber", target = "accountNumber")
    })
    AccountDto map(final Account account, final String accountNumber);

}
