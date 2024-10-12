package com.dbp.winproyect.auth.dto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Size;
import jakarta.validation.constraints.Email;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class EnterpriseDtoRegister {
    @NotNull
    private Long ruc;
    @NotNull
    @Email(message = "Debes proporcionar un correo electrónico válido")
    private String email;
    @NotNull
    private String password;
    @NotNull
    private BusinessSector businessSector;
    @NotNull
    private Size size;
}