package com.dbp.winproyect.client.domain;

import com.dbp.winproyect.client.dto.ClientDtoViewPerfilResponse;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }

    public ClientDtoViewPerfilResponse getClientProfile(Long clientId) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);

        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return convertToDto(client);
        } else {
            throw new ResourceNotFoundException("Client not found with ID: " + clientId);
        }
    }
    public ClientDtoViewPerfilResponse partialUpdateClientProfile(Long clientId, ClientDtoViewPerfilResponse clientDto) {
        // Buscar el cliente por ID
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found"));

        // Actualizar solo los campos que están presentes en el body
        if (clientDto.getFirstName() != null) {
            client.setFirstName(clientDto.getFirstName());
        }
        if (clientDto.getLastName() != null) {
            client.setLastName(clientDto.getLastName());
        }
        if (clientDto.getAddress() != null) {
            client.setAddress(clientDto.getAddress());
        }
        if (clientDto.getPhoneNumber() != null) {
            client.setPhoneNumber(clientDto.getPhoneNumber());
        }
        if (clientDto.getEmail() != null) {
            client.setEmail(clientDto.getEmail());
        }

        // Guardar los cambios
        clientRepository.save(client);

        // Mapear la entidad actualizada al DTO de respuesta
        return modelMapper.map(client, ClientDtoViewPerfilResponse.class);
    }

    private ClientDtoViewPerfilResponse convertToDto(Client client) {
        return modelMapper.map(client, ClientDtoViewPerfilResponse.class);
    }
}