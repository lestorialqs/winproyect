package com.dbp.winproyect.dto;

import lombok.Data;

@Data
public class FreelancerDto extends ProviderDto {
    private String firstName;
    private String lastName;
    private String speciality;
    private String experience;
    private String grade;
    private Long dni;
}
