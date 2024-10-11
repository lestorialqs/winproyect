package com.dbp.winproyect.dto;

import lombok.Data;

@Data
public class EnterpriseDto extends UserRequestDto {
    private Long phoneNumber;
    private String address; // direccion
    private Integer rating;
    private String category;
    private String name;
    private String experience;
}
