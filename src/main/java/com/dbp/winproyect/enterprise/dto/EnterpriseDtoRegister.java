package com.dbp.winproyect.enterprise.dto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Size;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EnterpriseDtoRegister {
    @NotNull
    private Long ruc;
    @NotNull
    private String name;
    @NotNull
    private BusinessSector businessSector;
    @NotNull
    private Size size;


}
