package com.dbp.winproyect.enterprise.domain.dto;

import lombok.Data;

@Data
public class EnterpriseDto {
    private String phoneNumber;
    private String address; // direccion
    private Integer rating;
    private String category;
    private String name;
    private String experience;
}
