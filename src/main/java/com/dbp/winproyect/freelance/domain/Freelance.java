package com.dbp.winproyect.freelance.domain;


import com.dbp.winproyect.provider.domain.Provider;
import jakarta.persistence.Entity;
import lombok.Data;

import java.io.Serializable;


@Data
@Entity
public class Freelance extends Provider {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer dni;
    private Integer experience;
    private LevelEducation levelEducation;



}
