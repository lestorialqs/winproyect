package com.dbp.winproyect.enterprise.domain;


import com.dbp.winproyect.provider.domain.Provider;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Enterprise extends Provider {
    private String name;
    private String description;
    private BusinessSector businessSector;


}
