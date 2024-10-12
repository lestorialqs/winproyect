package com.dbp.winproyect.enterprise.application;

import com.dbp.winproyect.enterprise.domain.EnterpriseService;
import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/enterprise")
@RequiredArgsConstructor
public class EnterpriseController {
    private final EnterpriseService enterpriseService;

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
        EnterpriseDtoViewPerfilResponse updatedProfile = enterpriseService.actualizarPerfilEmpresa(id, updates);
        return ResponseEntity.ok(updatedProfile);
    }
}