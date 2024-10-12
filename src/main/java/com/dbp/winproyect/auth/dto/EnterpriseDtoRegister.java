package com.dbp.winproyect.auth.dto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Size;
import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

@Data
public class EnterpriseDtoRegister {
    @NotNull
    private Long ruc;
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private BusinessSector businessSector;
    @NotNull
    private Size size;
}