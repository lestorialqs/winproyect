package com.dbp.winproyect.serviceEntity.domain;

import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.provider.domain.Provider;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private String address;
    private Double suggestedPrice;

    private Float avg_rating;

    //los tags xde eeeeeeeeeeeeeee ;
    @ElementCollection
    @CollectionTable(name = "service_tags", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;



}
