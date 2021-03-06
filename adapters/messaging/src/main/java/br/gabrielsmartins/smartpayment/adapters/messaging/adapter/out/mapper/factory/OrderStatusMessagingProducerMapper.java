package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.out.mapper.factory;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import org.apache.avro.specific.SpecificRecord;

public interface OrderStatusMessagingProducerMapper<T extends SpecificRecord> {

    T mapToMessage(Order order);

    OrderStatus getOrderStatus();

}
