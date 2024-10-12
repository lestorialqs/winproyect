package com.dbp.winproyect.freelance.domain.dto;

import com.dbp.winproyect.provider.dto.ProviderDto;
import lombok.Data;

@Data
public class FreelancerDto extends ProviderDto {
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;
    private String grade;
    private Long dni;
}
