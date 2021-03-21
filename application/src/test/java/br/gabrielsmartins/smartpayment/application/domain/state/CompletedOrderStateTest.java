package br.gabrielsmartins.smartpayment.application.domain.state;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.domain.enums.OrderStatus;
import br.gabrielsmartins.smartpayment.application.exception.IllegalStateOrderException;

public class CompletedOrderStateTest {
	
	private CompletedOrderState state;
	private Order order;
	
	@BeforeEach
	public void setup() {
		this.order = new Order();
		this.state = new CompletedOrderState(order);
	}
	
	@Test
	@DisplayName("Given State When Next Method Is Called Then Throw Exception")
	public void givenStateWhenNextMethodIsCalledThenThrowException() {
		assertThrows(IllegalStateOrderException.class, () -> this.state.next(order));
	}
	
	@Test
	@DisplayName("Given State When Get Status Method Is Called Then Return Order Status")
	public void givenStateWhenGetStatusMethodIsCalledThenReturnOrderStatus() {
		OrderStatus status = this.state.getStatus();
		assertThat(status).isEqualTo(OrderStatus.COMPLETED);
	}

}
