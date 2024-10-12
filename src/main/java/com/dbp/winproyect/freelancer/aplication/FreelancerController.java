package com.dbp.winproyect.freelancer.aplication;

import com.dbp.winproyect.freelancer.domain.FreelancerService;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/freelancer")
@RequiredArgsConstructor
public class FreelancerController {
    private final FreelancerService freelancerService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<FreelancerDtoViewPerfilResponse> verPerfilFreelancer(@PathVariable Long id) {
        FreelancerDtoViewPerfilResponse freelancerDto = freelancerService.obtenerPerfilFreelancer(id);
        return ResponseEntity.ok(freelancerDto);
    }


    @PatchMapping("profile/{id}")
    public ResponseEntity<FreelancerDtoViewPerfilResponse> updateFreelancerProfile(
            @PathVariable Long id,
            @RequestBody Map<String, Object> updates) {
        FreelancerDtoViewPerfilResponse updatedFreelancer = freelancerService.updateFreelancer(id, updates);
        return ResponseEntity.ok(updatedFreelancer);
    }
}