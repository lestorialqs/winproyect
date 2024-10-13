package com.dbp.winproyect.payment.infrastructure;

import com.dbp.winproyect.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    // Encontrar un pago por el id del contrato (arrangement)
    Optional<Payment> findByArrangementId(Long arrangementId);
}