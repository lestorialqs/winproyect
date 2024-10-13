package com.dbp.winproyect.freelancer.aplication;

import com.dbp.winproyect.auth.domain.DniValidationService;
import com.dbp.winproyect.freelancer.domain.FreelancerService;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/freelancer")
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;
    private DniValidationService dniValidationService;
    String token = "apis-token-10962.XDdecgSy4vPRkfxbYMLnPNJ6YUAwfQid";
    @GetMapping("/profile/{id}")
    public ResponseEntity<FreelancerDtoViewPerfilResponse> verPerfilFreelancer(@PathVariable Long id) {
        FreelancerDtoViewPerfilResponse freelancerDto = freelancerService.obtenerPerfilFreelancer(id);
        return ResponseEntity.ok(freelancerDto);
    }


    @PatchMapping("profile/{id}")
    public ResponseEntity<FreelancerDtoViewPerfilResponse> updateFreelancerProfile(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {

        // Validar si el campo 'dni' está presente en el 'updates'
        if (updates.containsKey("dni")) {
            String dni = updates.get("dni").toString();

            // Obtener el token antes de la validación del DNI
            boolean isDniValid = Boolean.TRUE.equals(dniValidationService.validateDni(dni, token).block());

            if (!isDniValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // DNI inválido
            }
        }

        // Actualizar el perfil del freelancer
        FreelancerDtoViewPerfilResponse updatedFreelancer = freelancerService.updateFreelancer(id, updates);
        return ResponseEntity.ok(updatedFreelancer);
    }

}