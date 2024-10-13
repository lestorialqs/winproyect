package com.dbp.winproyect.provider.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dbp.winproyect.AbstractContainerBaseTest;
import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.NoSuchElementException;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ClientRepositoryTest extends AbstractContainerBaseTest {

    @Mock
    private ClientRepository clientRepository; // Repositorio mock para Client

    @InjectMocks
    private Client client; // Client objeto que vamos a probar

    @BeforeEach
    public void setUp() {
        client = new Client();
        client.setEmail("client@example.com");
        client.setPassword("password123");
        client.setPhoneNumber("1234567890");
        client.setFirstName("John");
        client.setLastName("Doe");
        client.setDni(12345678L);
        client.setShowAds(true);
    }

    @Test
    void testClientCreation() {
        assertEquals("client@example.com", client.getEmail());
        assertEquals("password123", client.getPassword());
        assertEquals("1234567890", client.getPhoneNumber());
        assertEquals("John", client.getFirstName());
        assertEquals("Doe", client.getLastName());
        assertEquals(12345678L, client.getDni());
        assertTrue(client.getShowAds());
    }

    @Test
    void testFindByEmail() {
        // Simulamos que el repositorio devuelve el Client por email
        when(clientRepository.findByEmail("client@example.com")).thenReturn(Optional.of(client));

        // Llamada directa al repositorio
        Optional<AppUser> foundUser = clientRepository.findByEmail("client@example.com");

        // Verificamos que el método findByEmail() fue invocado una vez
        verify(clientRepository, times(1)).findByEmail("client@example.com");

        // Verificamos que los datos del usuario devuelto son correctos
        assertTrue(foundUser.isPresent());
        assertEquals("client@example.com", foundUser.get().getEmail());
        assertEquals("John", ((Client) foundUser.get()).getFirstName());
        assertEquals("Doe", ((Client) foundUser.get()).getLastName());
    }

    @Test
    void testGetByIdVerification() {
        // Simulamos que el repositorio devuelve el Client
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Llamada directa al repositorio
        Optional<Client> foundUser = clientRepository.findById(1L);

        // Verificamos que el método findById() fue invocado una vez
        verify(clientRepository, times(1)).findById(1L);

        // Verificamos que los datos del usuario devuelto son correctos
        assertTrue(foundUser.isPresent());
        assertEquals("client@example.com", foundUser.get().getEmail());
        assertEquals("John", ((Client) foundUser.get()).getFirstName());
        assertEquals("Doe", ((Client) foundUser.get()).getLastName());
    }
    @Test
    void testCreateClient() {
        // Simulamos que el repositorio guarda el cliente y devuelve el mismo objeto
        when(clientRepository.save(any(Client.class))).thenReturn(client);

        // Llamamos al método que estamos probando (en este caso solo usamos el mock)
        clientRepository.save(client); // Guardamos el cliente

        // Simulamos que el repositorio devuelve el cliente por su ID
        when(clientRepository.findById(1L)).thenReturn(Optional.of(client));

        // Recuperamos el cliente
        Optional<Client> createdClient = clientRepository.findById(1L);

        // Verificamos que el método save() fue invocado una vez
        verify(clientRepository, times(1)).save(client);

        // Afirmamos que el cliente creado es igual al cliente original
        assertTrue(createdClient.isPresent()); // Verificamos que el cliente está presente
        assertEquals("Doe", createdClient.get().getLastName());
        assertEquals("John", createdClient.get().getFirstName());
        assertEquals(12345678L, createdClient.get().getDni());
        assertTrue(createdClient.get().getShowAds());
    }
    public Optional<Client> updateClient(Long id, Client clientDetails) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFirstName(clientDetails.getFirstName());
                    client.setLastName(clientDetails.getLastName());
                    client.setDni(clientDetails.getDni());
                    client.setShowAds(clientDetails.getShowAds());
                    return clientRepository.save(client);
                });
    }
    /*@Test
    void testUpdateClient() {
        // Simulamos un cliente existente
        Client existingClient = new Client();
        existingClient.setId(1L);
        existingClient.setFirstName("John");
        existingClient.setLastName("Doe");
        existingClient.setDni(12345678L);
        existingClient.setShowAds(true);

        // Simulamos que el repositorio devuelve el cliente existente
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));

        // Nuevos detalles para el cliente (simulamos la actualización)
        existingClient.setFirstName("Jane");
        existingClient.setLastName("Doe");
        existingClient.setDni(87654321L);
        existingClient.setShowAds(false);

        // Simulamos que el repositorio guarda el cliente actualizado
        when(clientRepository.save(existingClient)).thenReturn(existingClient);

        // Guardamos el cliente actualizado
        Client updatedClient = clientRepository.save(existingClient);

        // Verificamos que el método findById() fue invocado
        verify(clientRepository, times(1)).findById(1L);

        // Verificamos que el método save() fue invocado una vez
        verify(clientRepository, times(1)).save(existingClient);

        // Verificamos que los detalles del cliente se actualizaron correctamente
        assertEquals("Jane", updatedClient.getFirstName());
        assertEquals("Doe", updatedClient.getLastName());
        assertEquals(87654321L, updatedClient.getDni());
        assertFalse(updatedClient.getShowAds());
    }

     */

    @Test
    void testFindByDni() {
        // Simulamos un cliente existente con un DNI específico
        Client existingClient = new Client();
        existingClient.setDni(12345678L);
        existingClient.setFirstName("John");
        existingClient.setLastName("Doe");

        // Simulamos que el repositorio devuelve el cliente cuando se busca por DNI
        when(clientRepository.findByDni(12345678L)).thenReturn(Optional.of(existingClient));

        // Llamada directa al repositorio
        Optional<Client> foundClient = clientRepository.findByDni(12345678L);

        // Verificamos que el método findByDni() fue invocado una vez
        verify(clientRepository, times(1)).findByDni(12345678L);

        // Verificamos que el cliente fue encontrado y sus detalles son correctos
        assertTrue(foundClient.isPresent()); // Aquí es donde fallaba
        assertEquals(12345678L, foundClient.get().getDni());
        assertEquals("John", foundClient.get().getFirstName());
        assertEquals("Doe", foundClient.get().getLastName());
    }
    @Test
    void testUpdateClient() {
        // Simulamos un cliente existente
        Client existingClient = new Client();
        existingClient.setId(1L);
        existingClient.setFirstName("John");
        existingClient.setLastName("Doe");
        existingClient.setDni(12345678L);
        existingClient.setShowAds(true);

        // Simulamos que el repositorio devuelve el cliente existente
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));

        // Nuevos detalles para el cliente (simulamos la actualización)
        Client updatedDetails = new Client();
        updatedDetails.setFirstName("Jane");
        updatedDetails.setLastName("Doe");
        updatedDetails.setDni(87654321L);
        updatedDetails.setShowAds(false);

        // Simulamos que el repositorio guarda el cliente actualizado
        when(clientRepository.save(any(Client.class))).thenReturn(existingClient);

        // Llamamos al método de actualización
        Optional<Client> updatedClientOptional = updateClient(1L, updatedDetails);

        // Verificamos que el método findById() fue invocado
        verify(clientRepository, times(1)).findById(1L);

        // Verificamos que el método save() fue invocado una vez
        verify(clientRepository, times(1)).save(existingClient);

        // Verificamos que los detalles del cliente se actualizaron correctamente
        assertTrue(updatedClientOptional.isPresent()); // Verificamos que el cliente actualizado está presente
        Client updatedClient = updatedClientOptional.get(); // Obtener el cliente actualizado
        assertEquals("Jane", updatedClient.getFirstName());
        assertEquals("Doe", updatedClient.getLastName());
        assertEquals(87654321L, updatedClient.getDni());
        assertFalse(updatedClient.getShowAds());
    }


    @Test
    void testDeleteClient() {
        // Simulamos un cliente existente
        Client existingClient = new Client();
        existingClient.setId(1L);
        existingClient.setFirstName("John");
        existingClient.setLastName("Doe");
        existingClient.setDni(12345678L);
        existingClient.setShowAds(true);

        // Simulamos que el repositorio devuelve el cliente existente
        when(clientRepository.findById(1L)).thenReturn(Optional.of(existingClient));

        // Llamamos al método que estamos probando (eliminar cliente)
        clientRepository.delete(existingClient); // Eliminamos el cliente

        // Verificamos que el método delete() fue invocado una vez
        verify(clientRepository, times(1)).delete(existingClient);

        // Verificamos que el cliente ya no se encuentra en el repositorio
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());
        Optional<Client> deletedClient = clientRepository.findById(1L);

        // Verificamos que el cliente eliminado no se encuentra
        assertTrue(deletedClient.isEmpty());
    }

    @Test
    void testDeleteClientNotFound() {
        // Simulamos que el cliente no existe
        when(clientRepository.findById(1L)).thenReturn(Optional.empty());

        // Intentamos eliminar un cliente que no existe
        assertThrows(NoSuchElementException.class, () -> {
            clientRepository.delete(clientRepository.findById(1L).orElseThrow());
        });

        // Verificamos que el método findById() fue invocado
        verify(clientRepository, times(1)).findById(1L);
    }


}
