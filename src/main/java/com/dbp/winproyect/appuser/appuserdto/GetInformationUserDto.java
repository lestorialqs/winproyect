package com.dbp.winproyect.appuser.appuserdto;

import lombok.Data;

import java.time.LocalDateTime;


@Data
public class GetInformationUserDto {
    private Long id_user;
    private String email;
    private String phoneNumber;
    private String address;
    private LocalDateTime registration_date;
    private String firstName;
    private String lastName;
    private Boolean showAds;
}
