package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.domain.ClientService;
import com.dbp.winproyect.client.dto.ClientDtoViewPerfilResponse;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientServiceTests {

    @Mock
    private ClientRepository clientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ClientService clientService;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar un cliente de prueba
        client = new Client();
        client.setId(1L);  // Si tienes el campo `id` en la clase base `AppUser`
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setDni(123456789L);
        client.setShowAds(true);
    }

    @Test
    void testGetClientProfile_Success() {
        // Preparar el mock del repositorio
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Preparar el mock del mapeo
        ClientDtoViewPerfilResponse clientDto = new ClientDtoViewPerfilResponse();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        when(modelMapper.map(any(Client.class), eq(ClientDtoViewPerfilResponse.class)))
                .thenReturn(clientDto);

        // Ejecutar el método a probar
        ClientDtoViewPerfilResponse result = clientService.getClientProfile(1L);

        // Verificar que el resultado sea correcto
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());

        // Verificar que se haya llamado al repositorio una vez
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testGetClientProfile_ClientNotFound() {
        // Preparar el mock para cuando el cliente no se encuentra
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Ejecutar el método y verificar que lance la excepción
        assertThrows(ResourceNotFoundException.class, () -> clientService.getClientProfile(1L));

        // Verificar que se haya llamado al repositorio una vez
        verify(clientRepository, times(1)).findById(1L);
    }

    @Test
    void testPartialUpdateClientProfile_Success() {
        // Preparar el cliente y el DTO de prueba
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        ClientDtoViewPerfilResponse clientDto = new ClientDtoViewPerfilResponse();
        clientDto.setFirstName("Jane");
        clientDto.setLastName("Smith");

        // Ejecutar el método de actualización parcial
        ClientDtoViewPerfilResponse result = clientService.partialUpdateClientProfile(1L, clientDto);

        // Verificar que los campos del cliente fueron actualizados correctamente
        assertEquals("Jane", client.getFirstName());
        assertEquals("Smith", client.getLastName());

        // Verificar que el cliente fue guardado en el repositorio
        verify(clientRepository, times(1)).save(client);
    }

    @Test
    void testPartialUpdateClientProfile_ClientNotFound() {
        // Preparar el mock para cuando el cliente no se encuentra
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        ClientDtoViewPerfilResponse clientDto = new ClientDtoViewPerfilResponse();

        // Ejecutar el método y verificar que lance la excepción
        assertThrows(ResourceNotFoundException.class, () -> clientService.partialUpdateClientProfile(1L, clientDto));

        // Verificar que se haya llamado al repositorio una vez
        verify(clientRepository, times(1)).findById(1L);
    }
    @Test
    void testConvertToDto() {
        // Preparar el DTO esperado
        ClientDtoViewPerfilResponse expectedDto = new ClientDtoViewPerfilResponse();
        expectedDto.setFirstName(client.getFirstName());
        expectedDto.setLastName(client.getLastName());

        // Simular la conversión de ModelMapper
        when(modelMapper.map(client, ClientDtoViewPerfilResponse.class)).thenReturn(expectedDto);

        // Ejecutar el método a probar
        ClientDtoViewPerfilResponse result = clientService.convertToDto(client);

        // Verificar que el resultado no es nulo
        assertNotNull(result);

        // Verificar que el mapeo se realizó correctamente
        assertEquals(expectedDto.getFirstName(), result.getFirstName());
        assertEquals(expectedDto.getLastName(), result.getLastName());

        // Verificar que el mapper fue invocado una vez
        verify(modelMapper, times(1)).map(client, ClientDtoViewPerfilResponse.class);
    }
}
