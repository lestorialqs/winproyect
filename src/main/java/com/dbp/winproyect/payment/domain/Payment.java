package com.dbp.winproyect.payment.domain;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private String currency;
    private LocalDateTime date;
    private Method method;

    @OneToOne
    @JoinColumn(name = "arrangement_id")
    private Arrangement arrangement;


}
