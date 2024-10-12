package com.dbp.winproyect.payment.domain;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.arrangement.infrastructure.ArrangementRepository;
import com.dbp.winproyect.payment.dto.PaymentRequestDto;
import com.dbp.winproyect.payment.infrastructure.PaymentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ArrangementRepository ArrangementRepository;


    //Permite a un cliente realizar un pago por un servicio.
    public Payment createPayment(PaymentRequestDto paymentRequestDto) {
        Arrangement Arrangement = ArrangementRepository.findById(paymentRequestDto.getArrangementId())
                .orElseThrow(() -> new RuntimeException("No se encontró el contrato"));

        Payment payment = modelMapper.map(paymentRequestDto, Payment.class);
        payment.setArrangement(Arrangement);
        payment.setDate(LocalDateTime.now());


        return paymentRepository.save(payment);
    }


    ///public
}
