package com.dbp.winproyect.auth.dto;


import jakarta.validation.constraints.Email;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
@Data
public class ClientDtoRegister {


    @NotNull
    @Email(message = "Debes proporcionar un correo electrónico válido")
    private String email ;
    @NotNull
    private String password;
    @NotNull
    private String phoneNumber;
    @NotNull
    private String address;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;


    private Long dni;



}