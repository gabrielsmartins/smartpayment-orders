package br.gabrielsmartins.smartpayment.adapters.web.adapter.in;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.gabrielsmartins.smartpayment.adapters.web.adapter.in.dto.OrderDTO;
import br.gabrielsmartins.smartpayment.adapters.web.mapper.in.OrderWebMapper;
import br.gabrielsmartins.smartpayment.application.domain.Order;
import br.gabrielsmartins.smartpayment.application.ports.in.SaveOrderUseCase;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class SubmitOrderController {

    private final SaveOrderUseCase saveOrderUseCase;
    private final OrderWebMapper mapper;

    @PostMapping
    public ResponseEntity<?> save(@RequestHeader HttpHeaders httpHeaders, @RequestBody OrderDTO orderDTO){
        Order order = mapper.mapToDomain(orderDTO);
        Order savedOrder = saveOrderUseCase.save(order);
        return new ResponseEntity<>(mapper.mapToDto(savedOrder), HttpStatus.ACCEPTED);
    }

}