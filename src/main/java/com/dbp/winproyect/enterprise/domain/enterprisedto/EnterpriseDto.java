package com.dbp.winproyect.enterprise.domain.enterprisedto;

import com.dbp.winproyect.appuser.domain.AppUser;
import lombok.Data;

@Data
public class EnterpriseDto extends AppUser.UserRequestDto {
    private Long phoneNumber;
    private String address; // direccion
    private Integer rating;
    private String category;
    private String name;
    private String experience;
}
