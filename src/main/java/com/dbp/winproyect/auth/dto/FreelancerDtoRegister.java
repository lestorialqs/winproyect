package com.dbp.winproyect.auth.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class FreelancerDtoRegister{
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    @Email(message = "Debes proporcionar un correo electrónico válido")
    private String email;
    @NotNull
    private String address;
    @NotNull
    private Long dni;
    @NotNull
    private Long phoneNumber;
    @NotNull
    private String password;
}