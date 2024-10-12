package com.dbp.winproyect.appuser.dto;

import lombok.Data;

@Data
public class AppUserRequestDto { // Este DTO es para cuando el usuario se registra o actualiza su información. Sólo necesitas los datos básicos.
    private String email;
    private String password;
    private String phoneNumber;
    private String address;
    private String type;  // Tipo de usuario: CLIENT o PROVIDER
}