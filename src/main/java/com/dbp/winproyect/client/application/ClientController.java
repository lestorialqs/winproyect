package com.dbp.winproyect.client.application;

import com.dbp.winproyect.client.domain.ClientService;
import com.dbp.winproyect.client.dto.ClientDtoViewPerfilResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // Obtener el perfil de un cliente por ID
    @GetMapping("/profile/{clientId}")
    public ResponseEntity<ClientDtoViewPerfilResponse> getClientProfile(@PathVariable Long clientId) {
        ClientDtoViewPerfilResponse clientProfile = clientService.getClientProfile(clientId);
        return ResponseEntity.ok(clientProfile);
    }

    @PatchMapping("/profile/{id}")
    public ResponseEntity<ClientDtoViewPerfilResponse> partialUpdateClientProfile(
            @PathVariable Long id,
            @RequestBody ClientDtoViewPerfilResponse clientDto) {

        ClientDtoViewPerfilResponse updatedProfile = clientService.partialUpdateClientProfile(id, clientDto);
        return ResponseEntity.ok(updatedProfile);
    }
}
