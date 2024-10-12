package com.dbp.winproyect.enterprise.domain.dto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.review.domain.Review;
import lombok.Data;

@Data

public class EnterpriseResponseDto {
    private String name;
    private String description;
    private String address;
    private String phoneNumber;
    private Integer rating;
    private BusinessSector businessSector;
    private String name;
    private Integer experienceInYears;  // Calculado a partir de `experience





}
