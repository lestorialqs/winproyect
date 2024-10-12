package com.dbp.winproyect.freelancer.domain;


import com.dbp.winproyect.provider.domain.Provider;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class Freelancer extends Provider {

    private String firstName;
    private String lastName;
    private Integer age;
    private Integer dni;
    private String experience;
    private LevelEducation levelEducation;



}
