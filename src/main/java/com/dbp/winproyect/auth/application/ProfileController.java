package com.dbp.winproyect.auth.application;

import com.dbp.winproyect.appuser.update.ClientUpdateDto;
import com.dbp.winproyect.appuser.update.EnterpriseUpdateDto;
import com.dbp.winproyect.appuser.update.FreelancerUpdateDto;
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

    @GetMapping
    public ResponseEntity<?> getProfile() {
        // Obtener el email del usuario autenticado desde el contexto de seguridad
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        // Verificar el rol y devolver el perfil adecuado
        if (role.contains("FREELANCER")) {
            Freelancer freelancer = freelancerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));
            return ResponseEntity.ok(freelancer);
        } else if (role.contains("ENTERPRISE")) {
            Enterprise enterprise = enterpriseRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Enterprise no encontrado"));
            return ResponseEntity.ok(enterprise);
        } else if (role.contains("CLIENT")) {
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Client no encontrado"));
            return ResponseEntity.ok(client);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Si no tiene un rol adecuado
    }
    private <T> T mapToDto(Map<String, Object> requestBody, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();  // Usa Jackson para mapear
        return objectMapper.convertValue(requestBody, dtoClass);
    }
    @PatchMapping
    public ResponseEntity<?> updateProfile(@RequestBody Map<String, Object> profileUpdateRequest) {
        // Obtener el email del usuario autenticado desde el contexto de seguridad
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        // Verificar el rol y actualizar el perfil adecuado
        if (role.contains("FREELANCER")) {
            Freelancer freelancer = freelancerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));

            // Mapear el objeto profileUpdateRequest a FreelancerUpdateDto
            FreelancerUpdateDto freelancerDto = mapToDto(profileUpdateRequest, FreelancerUpdateDto.class);

            // Actualizar campos específicos de Freelancer
            freelancer.setFirstName(freelancerDto.getFirstName());
            freelancer.setLastName(freelancerDto.getLastName());
            freelancer.setAge(freelancerDto.getAge());
            freelancer.setDni(freelancerDto.getDni());
            freelancer.setExperience(freelancerDto.getExperience());
            freelancer.setLevelEducation(freelancerDto.getLevelEducation());

            freelancerRepository.save(freelancer);
            return ResponseEntity.ok(freelancer);

        } else if (role.contains("ENTERPRISE")) {
            Enterprise enterprise = enterpriseRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Enterprise no encontrado"));

            // Mapear el objeto profileUpdateRequest a EnterpriseUpdateDto
            EnterpriseUpdateDto enterpriseDto = mapToDto(profileUpdateRequest, EnterpriseUpdateDto.class);

            // Actualizar campos específicos de Enterprise
            enterprise.setName(enterpriseDto.getName());
            enterprise.setDescription(enterpriseDto.getDescription());
            enterprise.setBusinessSector(enterpriseDto.getBusinessSector());
            enterprise.setSize(enterpriseDto.getSize());
            enterprise.setAddress(enterpriseDto.getAddress());

            enterpriseRepository.save(enterprise);
            return ResponseEntity.ok(enterprise);

        } else if (role.contains("CLIENT")) {
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Client no encontrado"));

            // Mapear el objeto profileUpdateRequest a ClientUpdateDto
            ClientUpdateDto clientDto = mapToDto(profileUpdateRequest, ClientUpdateDto.class);

            // Actualizar campos específicos de Client
            client.setFirstName(clientDto.getFirstName());
            client.setLastName(clientDto.getLastName());
            client.setDni(clientDto.getDni());
            client.setShowAds(clientDto.getShowAds());

            clientRepository.save(client);
            return ResponseEntity.ok(client);
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();  // Si no tiene un rol adecuado
    }

}