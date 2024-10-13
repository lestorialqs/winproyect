package com.dbp.winproyect.payment.application;

import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.payment.domain.Payment;
import com.dbp.winproyect.payment.domain.PaymentService;
import com.dbp.winproyect.payment.dto.PaymentRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/service")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // Endpoint para crear un pago asociado a un contrato
    @PostMapping("/{idService}/arrangement/{idArrangement}/payment")
    public ResponseEntity<Void> createPayment(@PathVariable Long idArrangement,
                                              @RequestBody PaymentRequestDto paymentRequestDto) {
        Payment payment = paymentService.createPayment(idArrangement, paymentRequestDto);
        URI uri = URI.create("/service/arrangement/" + idArrangement + "/payment/" + payment.getId());
        return ResponseEntity.created(uri).build();
    }

    // Endpoint para obtener un pago asociado a un contrato
    @GetMapping("/arrangement/{idArrangement}/payment")
    public ResponseEntity<Payment> getPaymentByArrangement(@PathVariable Long idArrangement) {
        return paymentService.getPaymentByArrangement(idArrangement)
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new ResourceNotFoundException("Pago no encontrado para este contrato."));
    }



}