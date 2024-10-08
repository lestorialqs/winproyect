package com.dbp.winproyect.review.domain;


import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.user.domain.User;
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
    @MapsId("service_id")
    private ServiceEntity serviceEntity;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("author_id")
    private User author;        

}
