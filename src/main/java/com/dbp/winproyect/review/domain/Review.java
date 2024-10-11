package com.dbp.winproyect.review.domain;


import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import jakarta.persistence.*;
import org.springframework.data.convert.Jsr310Converters;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String content;
    private LocalDateTime date;
    private Float rating;
    private Boolean editable;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Client author;


}
