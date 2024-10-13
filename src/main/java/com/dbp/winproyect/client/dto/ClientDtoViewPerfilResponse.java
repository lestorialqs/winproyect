package com.dbp.winproyect.client.dto;

import lombok.Data;

@Data
public class ClientDtoViewPerfilResponse {
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String email;
}
