package com.dbp.winproyect.auth.dto;


import lombok.Data;

@Data
public class UserLoginDto {
    private String email;
    private String password;

}