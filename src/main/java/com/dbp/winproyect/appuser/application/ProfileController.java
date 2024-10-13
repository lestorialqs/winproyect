package com.dbp.winproyect.appuser.application;

import com.dbp.winproyect.appuser.domain.AppUserService;
import com.dbp.winproyect.appuser.update.ClientUpdateDto;
import com.dbp.winproyect.appuser.update.EnterpriseUpdateDto;
import com.dbp.winproyect.appuser.update.FreelancerUpdateDto;
import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.arrangement.domain.ArrangementService;
import com.dbp.winproyect.auth.domain.DniValidationService;
import com.dbp.winproyect.auth.domain.RucValidationService;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AppUserService appUserService;

    @Autowired
    private ArrangementService arrangementService;

    @Autowired
    private DniValidationService dniValidationService;

    @Autowired
    private RucValidationService rucValidationService;


    String token = "apis-token-10962.XDdecgSy4vPRkfxbYMLnPNJ6YUAwfQid";


    @GetMapping
    public ResponseEntity<?> getProfile() {
        try {
            Object profile = appUserService.getProfile();
            return ResponseEntity.ok(profile);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    private <T> T mapToDto(Map<String, Object> requestBody, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();  // Usa Jackson para mapear
        return objectMapper.convertValue(requestBody, dtoClass);
    }

    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> profileUpdateRequest) {
        try {
            // Validar si el campo 'dni' está presente
            if (profileUpdateRequest.containsKey("dni")) {
                String dni = profileUpdateRequest.get("dni").toString();
                // Validar el DNI
                boolean isDniValid = Boolean.TRUE.equals(dniValidationService.validateDni(dni, token).block());
                if (!isDniValid) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DNI inválido");
                }
            }

            // Validar si el campo 'ruc' está presente
            if (profileUpdateRequest.containsKey("ruc")) {
                String ruc = profileUpdateRequest.get("ruc").toString();
                // Validar el RUC
                boolean isRucValid = Boolean.TRUE.equals(rucValidationService.validateRuc(ruc, token).block());
                if (!isRucValid) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RUC inválido");
                }
            }

            // Actualizar el perfil si las validaciones pasaron
            Object updatedProfile = appUserService.updateProfile(profileUpdateRequest);
            return ResponseEntity.ok(updatedProfile);

        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(e.getMessage());
        }
    }

    @GetMapping("/profile/arrangement")
    public ResponseEntity<List<Arrangement>> getUserArrangements(Principal principal) {
        List<Arrangement> arrangements = arrangementService.findByClientId(principal);
        return ResponseEntity.ok(arrangements);
    }

    @GetMapping("/profile/arrangement/{id}")
    public ResponseEntity<Arrangement> getUserArrangement(@PathVariable Long id, Principal principal) {
        // Llamar al servicio pasando el ID y el Principal
        Arrangement arrangement = arrangementService.getArrangementByIdAndUser(id, principal);
        return ResponseEntity.ok(arrangement);
    }

}
