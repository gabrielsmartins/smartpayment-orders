package br.gabrielsmartins.smartpayment.application.ports.in;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import reactor.core.publisher.Mono;

public interface SaveOrderUseCase {

	Mono<Order> save(Order order);

}
