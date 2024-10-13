package com.dbp.winproyect.arrangement.application;


import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.arrangement.domain.ArrangementService;
import com.dbp.winproyect.arrangement.dto.ArrangementRequestDto;
import jakarta.validation.constraints.DecimalMax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.AbstractRabbitListenerContainerFactoryConfigurer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.security.Principal;

@RestController
@RequestMapping("/service")
public class ArrangementController {
    @Autowired
    private ArrangementService arrangementService;

    @PostMapping("/{idService}/arrangement")
    public ResponseEntity<Void> createArrangement(@PathVariable Long idService,
                                                  @RequestBody ArrangementRequestDto arrangementRequestDto) {

        Arrangement arrangement1 = arrangementService.createArrangement(arrangementRequestDto,idService);
        URI uri = URI.create("/service/" + idService + "/arrangement/" + arrangement1.getId());
        return ResponseEntity.created(uri).build();
    }

    @DeleteMapping("/{idService}/arrangement")
    public ResponseEntity<Void> deletePendingArrangement(@PathVariable Long idService, Principal principal) {
        // Obtener el email del cliente autenticado desde el principal
        String email = principal.getName();

        // Buscar el arrangement pendiente
        arrangementService.deletePendingArrangement(idService, email);

        // Retornar una respuesta exitosa
        return ResponseEntity.noContent().build();
    }
}
