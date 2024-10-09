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
<<<<<<< HEAD
    //los tags xde eeeeeeeeeeeeeee ;
    @ElementCollection
    @CollectionTable(name = "service_tags", joinColumns = @JoinColumn(name = "service_id"))
    @Column(name = "tag")
    private Set<String> tags = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "providerja_id")
    private Provider provider;


=======
>>>>>>> 4b5f867e9b78bb5887a93256a4876899901f7cca
}
