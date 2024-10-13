package com.dbp.winproyect.payment.dto;

import com.dbp.winproyect.payment.domain.Method;
import lombok.Data;

@Data

public class PaymentRequestDto {

    private Double amount;
    private String currency;
    private Method method;  // Puede ser un enum o un string con el método de pago

    // Getters y setters
}