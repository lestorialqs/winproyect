package com.dbp.winproyect.arrangement.domain;


import com.dbp.winproyect.arrangement.dto.ArrangementRequestDto;
import com.dbp.winproyect.arrangement.infrastructure.ArrangementRepository;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.domain.ClientService;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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



//    public List<Arrangement> findByClientId(Long clientId) {
//
//        Client client = clientRepository.findById(clientId)
//                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));
//
//        List
//
//        return
//    }
    //.getArrangementByIdAndUser(id, principal.getName());
    public void createArrangement(ArrangementRequestDto arrangementRequestDto, Long serviceId) {

        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();


        Arrangement arrangement = new Arrangement();

        arrangement.setClient(clientRepository
                .findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Cliente no existe.")));


        arrangement.setServiceEntity(serviceEntityRepository
                .findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio con el id " + serviceId + " no existe.")));
        arrangement.setDate(LocalDateTime.now());
        arrangement.setStatus(Status.ACCEPTED);
        arrangementRepository.save(arrangement);

    }
}
