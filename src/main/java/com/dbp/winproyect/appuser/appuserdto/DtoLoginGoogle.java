package com.dbp.winproyect.appuser.appuserdto;


import lombok.Data;

@Data
public class DtoLoginGoogle {
    private String email;
    private String phoneNumber;
    private String address;
}