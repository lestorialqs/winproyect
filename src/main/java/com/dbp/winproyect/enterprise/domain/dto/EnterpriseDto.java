package com.dbp.winproyect.enterprise.domain.dto;

import com.dbp.winproyect.appuser.dto.UserRequestDto;
import lombok.Data;

@Data
public class EnterpriseDto extends UserRequestDto {
    private String RUC;
    private String email;
    private Integer rating;
    private String password;
    private String name;
    private String experience;
}
