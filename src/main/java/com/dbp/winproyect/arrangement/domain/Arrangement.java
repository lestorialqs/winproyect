package com.dbp.winproyect.arrangement.domain;

import ch.qos.logback.core.status.Status;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.payment.domain.Payment;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
@Entity
public class Arrangement {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private LocalDate date;
    private Status status;

    @ManyToOne
    @JoinColumn(name = "client_id",nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceEntity;






}
