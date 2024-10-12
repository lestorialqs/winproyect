package com.dbp.winproyect.serviceEntity.domain;

import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.tag.domain.Tag;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Getter
@Setter
@Entity
public class ServiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String description;
    private String address;
    private Double suggestedPrice;

    private Float avg_rating;

    @ManyToMany
    private Set<Tag> tags = new HashSet<>();


    @ManyToOne
    @JoinColumn(name = "provider_id", nullable = false)
    private Provider provider;
    



}
