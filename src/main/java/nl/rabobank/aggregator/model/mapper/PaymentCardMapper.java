package nl.rabobank.aggregator.model.mapper;

import nl.rabobank.aggregator.model.domain.CreditCard;
import nl.rabobank.aggregator.model.domain.DebitCard;
import nl.rabobank.aggregator.model.domain.PaymentCard;
import nl.rabobank.aggregator.model.domain.PaymentCardType;
import nl.rabobank.aggregator.model.dto.PaymentCardDto;
import org.mapstruct.BeforeMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public abstract class PaymentCardMapper {

    public static final PaymentCardMapper INSTANCE = Mappers.getMapper(PaymentCardMapper.class);

    @BeforeMapping
    protected void enrichtWithCardType(PaymentCard card, @MappingTarget PaymentCardDto paymentCardDto) {
        if (card instanceof DebitCard) {
            paymentCardDto.setType(PaymentCardType.DEBIT_CARD);
        }
        if (card instanceof CreditCard) {
            paymentCardDto.setType(PaymentCardType.CREDIT_CARD);
        }
    }

    public abstract PaymentCardDto map(PaymentCard card);

    public abstract List<PaymentCardDto> map(List<? extends PaymentCard> cards);
}
