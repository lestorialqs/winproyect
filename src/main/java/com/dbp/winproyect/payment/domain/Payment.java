package com.dbp.winproyect.payment.domain;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Double amount;
    private String currency;
    private Date date;
    private Method method;

    @OneToOne
    @JoinColumn(name = "arrangement_id")
    private Arrangement arrangement;


}
