package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper;


import br.gabrielsmartins.schemas.order_requested.Item;
import br.gabrielsmartins.smartpayment.application.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

public class OrderItemMessagingConsumerMapperTest {

    private OrderItemMessagingConsumerMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new OrderItemMessagingConsumerMapper();
    }

    @Test
    @DisplayName("Given Item Message When Map Then Return Order Item")
    public void givenItemMessageWhenMapThenReturnOrderItem(){

        Item item = Item.newBuilder()
                .setProductId(UUID.randomUUID().toString())
                .setQuantity(1)
                .setAmount(BigDecimal.valueOf(100.00))
                .build();

        OrderItem orderItem = this.mapper.mapToDomain(item);

        assertThat(orderItem).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(orderItem.getProductId().toString()).isEqualTo(item.getProductId());
        assertThat(orderItem.getQuantity()).isEqualTo(item.getQuantity());
        assertThat(orderItem.getAmount()).isEqualTo(item.getAmount());
    }
}
