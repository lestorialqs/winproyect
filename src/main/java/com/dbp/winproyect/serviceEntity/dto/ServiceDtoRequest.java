package com.dbp.winproyect.serviceEntity.dto;

import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.tag.domain.Tag;
import lombok.Data;

import java.util.Set;
@Data
public class ServiceDtoRequest {


    private String description;
    private String name;
    private String address;
    private Double suggestedPrice;
    private Set<Tag> tagSet;
    private Provider provider;

    
}
