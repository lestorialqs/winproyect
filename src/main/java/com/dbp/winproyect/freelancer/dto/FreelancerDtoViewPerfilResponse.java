package com.dbp.winproyect.freelancer.dto;

import com.dbp.winproyect.freelancer.domain.LevelEducation;
import lombok.Data;

@Data
public class FreelancerDtoViewPerfilResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private String email;
    private Long dni;
    private LevelEducation levelEducation;
    private String experience;
    private String speciality;
}