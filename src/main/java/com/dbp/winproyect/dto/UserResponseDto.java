package com.dbp.winproyect.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data // Este DTO es para devolver la información del usuario después de realizar acciones como obtener el perfil o la información de contacto del usuario.
public class UserResponseDto {
    private Long id_user;
    private String email;
    private String phoneNumber;
    private String address;
    private String type;
    private LocalDateTime registration_date;
}