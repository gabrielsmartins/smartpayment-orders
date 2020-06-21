package br.gabrielsmartins.smartpayment.messaging.adapters.messaging.mapper;

import br.gabrielsmartins.smartpayment.messaging.adapters.messaging.message.OrderMessage;
import br.gabrielsmartins.smartpayment.messaging.application.domain.enums.PaymentType;
import br.gabrielsmartins.smartpayment.messaging.application.domain.orders.Order;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface OrderMessagingMapper {

    @Mapping(source = "paymentType", target = "paymentType", qualifiedByName = "toType")
    Order mapToDomain(OrderMessage orderMessage);

    default PaymentType toType(String paymentTypeDescription){
        return PaymentType.fromDescription(paymentTypeDescription);
    }


}
