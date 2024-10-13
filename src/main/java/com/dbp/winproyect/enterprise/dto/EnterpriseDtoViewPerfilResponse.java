package com.dbp.winproyect.enterprise.dto;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Size;
import lombok.Data;

@Data
public class EnterpriseDtoViewPerfilResponse {
    private Long ruc;  // Registro Único de Contribuyente
    private String description;
    private String email;
    private String address;
    private Size size;  // Asegúrate de que este enum esté definido
    private BusinessSector businessSector;  // Asegúrate de que este enum esté definido
}