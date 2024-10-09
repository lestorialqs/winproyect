package com.dbp.winproyect.review.domain;


import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String content;
    private Timestamp date;
    private Float rating;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_id", nullable = false)
    private ServiceEntity serviceEntity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Client author;


}
