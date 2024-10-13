package com.dbp.winproyect.arrangement.domain;


import com.dbp.winproyect.arrangement.dto.ArrangementRequestDto;
import com.dbp.winproyect.arrangement.infrastructure.ArrangementRepository;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.domain.ClientService;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.exceptions.UnauthorizedOperationException;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ArrangementService {

    @Autowired
    private ArrangementRepository arrangementRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ServiceEntityRepository serviceEntityRepository;



    public List<Arrangement> findByClientId(Principal principal) {
        String email = principal.getName();  // Usar Principal directamente
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        return arrangementRepository.findAllByClient_Id(client.getId())
                .orElseThrow( () -> new ResourceNotFoundException("Arrangements not found"));
    }
    public Arrangement getArrangementByIdAndUser(Long id, Principal principal) {
        // Obtener el email directamente del principal
        String emailPrincipal = principal.getName();

        // Buscar el cliente por email
        Client client = clientRepository.findByEmail(emailPrincipal)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        // Buscar el contrato por ID y el ID del cliente autenticado
        Arrangement arrangement = arrangementRepository.findByIdAndClient_Id(id, client.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Arrangement not found"));

        return arrangement;
    }
    //.getArrangementByIdAndUser(id, principal.getName());
    public Arrangement createArrangement(ArrangementRequestDto arrangementRequestDto, Long serviceId) {
        // Obtener el email del usuario autenticado
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        // Obtener el cliente autenticado
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no existe."));

        // Obtener el servicio asociado al contrato
        ServiceEntity serviceEntity = serviceEntityRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con el id " + serviceId + " no existe."));

        // Verificar si el cliente tiene algún contrato abierto con este proveedor en este servicio
        List<Arrangement> openArrangements = arrangementRepository
                .findAllByClientAndServiceEntityAndStatusNot(client, serviceEntity, Status.CANCELLED);

        if (!openArrangements.isEmpty()) {
            throw new IllegalArgumentException("Ya tienes un contrato abierto con este proveedor. Cierra el contrato antes de crear uno nuevo.");
        }

        // Si no hay contratos abiertos, procedemos a crear el nuevo contrato
        Arrangement arrangement = new Arrangement();

        arrangement.setClient(clientRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no existe.")));


        arrangement.setServiceEntity(serviceEntityRepository
                .findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con el id " + serviceId + " no existe.")));
        arrangement.setDate(LocalDateTime.now());
        arrangement.setStatus(Status.ACCEPTED);  // Estado inicial del contrato
        arrangement.setPrice(arrangementRequestDto.getPrice());

        // Guardar el contrato en la base de datos
        arrangementRepository.save(arrangement);
        return arrangement;
    }
    public void deletePendingArrangement(Long serviceId, String email) {
        Client client = clientRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

        // Buscar el arrangement pendiente por servicio y cliente
        Arrangement arrangement = arrangementRepository
                .findByServiceEntity_IdAndClient_IdAndStatus(serviceId, client.getId(), Status.ACCEPTED)
                .orElseThrow(() -> new ResourceNotFoundException("No hay arrangement pendiente para este cliente y servicio"));

        // Eliminar el arrangement encontrado
        arrangementRepository.delete(arrangement);
    }
}
