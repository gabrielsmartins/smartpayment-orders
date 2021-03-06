package br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in;


import br.gabrielsmartins.schemas.order_requested.OrderRequested;
import br.gabrielsmartins.smartpayment.adapters.messaging.adapter.in.mapper.OrderMessagingConsumerMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase;
import br.gabrielsmartins.smartpayment.application.ports.in.SubmitOrderUseCase.SubmitOrderCommand;
import br.gabrielsmartins.smartpayment.common.stereotype.MessagingAdapter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;

@MessagingAdapter
@KafkaListener(topics = {"${topic.input.order-requested}"})
@Slf4j
@RequiredArgsConstructor
public class OrderConsumer {

    private final SubmitOrderUseCase useCase;
    private final OrderMessagingConsumerMapper mapper;

    @KafkaHandler
    public void consume(@Headers MessageHeaders headers,  @Payload OrderRequested message){
        log.info("Receiving new order: {},{}", headers, message);
        Order order = this.mapper.mapToDomain(message);
        var command = new SubmitOrderCommand(order);
        useCase.submit(command);
        log.info("Order submitted successfully: {}", order);
    }

    @KafkaHandler(isDefault = true)
    public void consume(Object object){
      log.warn("Unrecognized message: {}", object);
    }

}
