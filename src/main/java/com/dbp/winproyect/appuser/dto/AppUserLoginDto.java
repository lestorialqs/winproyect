package com.dbp.winproyect.appuser.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class AppUserLoginDto {
    @NotNull
    private String email;
    @NotNull
    private String password;
}