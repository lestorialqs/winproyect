package com.dbp.winproyect.tag.domain;

import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, nullable = false)
    private ServiceTag name;

    @ManyToMany(mappedBy = "tags")
    private Set<ServiceEntity> services = new HashSet<>();
}