package com.dbp.winproyect.freelance.domain.dto;

import com.dbp.winproyect.provider.dto.ProviderDto;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FreelancerDtoRegister extends ProviderDto {

    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email
    private String email;
    @NotNull
    private String address;
    @NotNull
    private Long dni;
}
