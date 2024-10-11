package com.dbp.winproyect.enterprise.domain.enterprisedto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import lombok.Data;

@Data
public class EnterpriseResponseDto {
    private Long phoneNumber;
    private String address;
    private Integer rating;
    private BusinessSector businessSector;
    private String name;
    private Integer experienceInYears;  // Calculado a partir de `experience`
}
