package com.dbp.winproyect.freelance.domain.freelancerdto;

import com.dbp.winproyect.provider.providerdto.ProviderDto;
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
