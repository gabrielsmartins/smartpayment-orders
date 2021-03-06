package br.gabrielsmartins.smartpayment.adapters.persistence.mapper;

import br.gabrielsmartins.smartpayment.adapters.persistence.entity.PaymentMethodEntity;
import br.gabrielsmartins.smartpayment.application.domain.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static br.gabrielsmartins.smartpayment.adapters.persistence.support.PaymentMethodEntitySupport.defaultPaymentMethodEntity;
import static br.gabrielsmartins.smartpayment.application.support.PaymentMethodSupport.defaultPaymentMethod;
import static org.assertj.core.api.Assertions.assertThat;

public class PaymentMethodPersistenceMapperTest {

    private PaymentMethodPersistenceMapper mapper;

    @BeforeEach
    public void setup(){
        this.mapper = new PaymentMethodPersistenceMapperImpl();
    }

    @Test
    @DisplayName("Given Payment Method When Map Then Return Payment Method Entity")
    public void givenPaymentMethodWhenMapThenReturnPaymentMethodEntity(){

        PaymentMethod paymentMethod = defaultPaymentMethod().build();

        PaymentMethodEntity paymentMethodEntity = this.mapper.mapToEntity(paymentMethod);

        assertThat(paymentMethodEntity).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(paymentMethodEntity.getPaymentType()).isEqualTo(paymentMethod.getPaymentType().getDescription());
        assertThat(paymentMethodEntity.getAmount()).isEqualTo(paymentMethod.getAmount());
    }

    @Test
    @DisplayName("Given Payment Method Entity When Map Then Return Payment Method")
    public void givenPaymentMethodEntityWhenMapThenReturnPaymentMethod(){
        PaymentMethodEntity paymentMethodEntity = defaultPaymentMethodEntity().build();

        PaymentMethod paymentMethod = this.mapper.mapToDomain(paymentMethodEntity);

        assertThat(paymentMethod).hasNoNullFieldsOrPropertiesExcept("order");
        assertThat(paymentMethod.getPaymentType().getDescription()).isEqualTo(paymentMethodEntity.getPaymentType());
        assertThat(paymentMethod.getAmount()).isEqualTo(paymentMethodEntity.getAmount());
    }
}
