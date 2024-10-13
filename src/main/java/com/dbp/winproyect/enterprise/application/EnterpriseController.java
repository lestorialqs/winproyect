package com.dbp.winproyect.enterprise.application;

import com.dbp.winproyect.auth.domain.RucValidationService;
import com.dbp.winproyect.enterprise.domain.EnterpriseService;
import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {

    @Autowired
    private final EnterpriseService enterpriseService;

    @Autowired
    private RucValidationService rucValidationService;

    String token = "apis-token-10962.XDdecgSy4vPRkfxbYMLnPNJ6YUAwfQid";

    // Endpoint para obtener el perfil de una empresa
    @GetMapping("profile/{id}")
    public ResponseEntity<EnterpriseDtoViewPerfilResponse> getEnterpriseProfile(@PathVariable Long id) {
        EnterpriseDtoViewPerfilResponse enterpriseProfile = enterpriseService.obtenerPerfilEmpresa(id);
        return ResponseEntity.ok(enterpriseProfile);
    }

    // Endpoint para actualizar el perfil de una empresa
    @PatchMapping("profile/{id}")
    public ResponseEntity<EnterpriseDtoViewPerfilResponse> updateEnterpriseProfile(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        // Validar si el campo 'ruc' está presente en el 'updates'
        if (updates.containsKey("ruc")) {
            String ruc = updates.get("ruc").toString();

            // Obtener el token antes de la validación del RUC
            boolean isRucValid = Boolean.TRUE.equals(rucValidationService.validateRuc(ruc, token).block());

            if (!isRucValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // RUC inválido
            }
        }

        // Actualizar el perfil de la empresa
        EnterpriseDtoViewPerfilResponse updatedProfile = enterpriseService.actualizarPerfilEmpresa(id, updates);
        return ResponseEntity.ok(updatedProfile);
    }

}