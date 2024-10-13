package com.dbp.winproyect.payment.domain;

import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.arrangement.domain.Status;
import com.dbp.winproyect.arrangement.infrastructure.ArrangementRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.payment.dto.PaymentRequestDto;
import com.dbp.winproyect.payment.infrastructure.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ArrangementRepository arrangementRepository;

    // Crear un pago vinculado a un contrato
    public Payment createPayment(Long arrangementId, PaymentRequestDto paymentRequestDto) {
        // Buscar el contrato (arrangement)
        Arrangement arrangement = arrangementRepository.findById(arrangementId)
                .orElseThrow(() -> new ResourceNotFoundException("Contrato no encontrado"));

        // Verificar que el contrato aún no haya sido pagado
        if (arrangement.getStatus() == Status.CANCELLED) {
            throw new IllegalStateException("El contrato ya está cerrado, no se pueden realizar pagos.");
        }

        // Verificar si ya existe un pago asociado a este contrato
        Optional<Payment> existingPayment = paymentRepository.findByArrangementId(arrangementId);
        if (existingPayment.isPresent()) {
            throw new IllegalStateException("Ya existe un pago asociado a este contrato.");
        }

        // Crear un nuevo pago
        Payment payment = new Payment();
        payment.setAmount(paymentRequestDto.getAmount());
        payment.setCurrency(paymentRequestDto.getCurrency());
        payment.setDate(LocalDateTime.now());
        payment.setMethod(paymentRequestDto.getMethod());  // Debes definir un enum o clase Method para los métodos de pago
        payment.setArrangement(arrangement);

        // Guardar el pago en el repositorio
        paymentRepository.save(payment);

        // Cambiar el estado del contrato a 'PAID'
        arrangement.setStatus(Status.CANCELLED);
        arrangementRepository.save(arrangement);

        return payment;
    }

    // Obtener el pago por contrato
    public Optional<Payment> getPaymentByArrangement(Long arrangementId) {
        return paymentRepository.findByArrangementId(arrangementId);
    }
}
