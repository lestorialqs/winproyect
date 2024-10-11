package com.dbp.winproyect.enterprise.domain.dto;

import com.dbp.winproyect.appuser.dto.UserRequestDto;
import lombok.Data;

@Data
public class EnterpriseDto extends UserRequestDto {
    private String phoneNumber;
    private String address; // direccion
    private Integer rating;
    private String category;
    private String name;
    private String experience;
}
