package com.dbp.winproyect.provider.infrastructure;

import com.dbp.winproyect.AbstractContainerBaseTest;
import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.provider.infrastructure.ProviderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional // Para realizar rollback después de cada test
public class ProviderRepositoryTest extends AbstractContainerBaseTest {

    @Autowired
    private ProviderRepository providerRepository;

    @Test
    public void testProviderCreation() {
        Provider provider = new Provider();
        provider.setRuc(987654321L);
        provider.setEstate(true);
        provider.setRating(4.5f);
        provider.setComission(0.2);
        provider.setEmail("provider@example.com");
        provider.setPhoneNumber("1122334455");

        Provider savedProvider = providerRepository.save(provider);

        assertNotNull(savedProvider.getId()); // Verifica que el ID fue generado
        assertEquals(987654321L, savedProvider.getRuc());
        assertTrue(savedProvider.getEstate());
        assertEquals(4.5f, savedProvider.getRating());
        assertEquals(0.2, savedProvider.getComission());
        assertEquals("provider@example.com", savedProvider.getEmail());
        assertEquals("1122334455", savedProvider.getPhoneNumber());
    }

    @Test
    public void testFindById() {
        Provider provider = new Provider();
        provider.setRuc(123456789L);
        provider.setEstate(true);
        provider.setRating(4.0f);
        provider.setComission(0.15);
        provider.setEmail("provider2@example.com");
        provider.setPhoneNumber("9988776655");

        // Guarda el proveedor
        Provider savedProvider = providerRepository.save(provider);

        // Busca el proveedor por su ID
        Provider foundProvider = providerRepository.findById(savedProvider.getId()).orElse(null);

        // Verifica que el proveedor fue encontrado
        assertNotNull(foundProvider);
        assertEquals(savedProvider.getId(), foundProvider.getId());
    }

    @Test
    public void testDeleteById() {
        Provider provider = new Provider();
        provider.setRuc(111222333L);
        provider.setEstate(true);
        provider.setRating(4.2f);
        provider.setComission(0.1);
        provider.setEmail("provider3@example.com");
        provider.setPhoneNumber("5566778899");

        // Guarda el proveedor
        Provider savedProvider = providerRepository.save(provider);

        // Verifica que el proveedor fue guardado
        assertNotNull(savedProvider.getId());

        // Elimina el proveedor
        providerRepository.deleteById(savedProvider.getId());

        // Verifica que el proveedor fue eliminado
        Provider deletedProvider = providerRepository.findById(savedProvider.getId()).orElse(null);
        assertNull(deletedProvider);
    }

    @Test
    public void testUpdateProvider() {
        Provider provider = new Provider();
        provider.setRuc(222333444L);
        provider.setEstate(true);
        provider.setRating(4.0f);
        provider.setComission(0.15);
        provider.setEmail("provider4@example.com");
        provider.setPhoneNumber("1231231234");

        // Guarda el proveedor
        Provider savedProvider = providerRepository.save(provider);

        // Actualiza los detalles del proveedor
        savedProvider.setRating(4.8f);
        savedProvider.setComission(0.18);
        Provider updatedProvider = providerRepository.save(savedProvider);

        // Verifica que los detalles se hayan actualizado correctamente
        assertEquals(4.8f, updatedProvider.getRating());
        assertEquals(0.18, updatedProvider.getComission());
    }

    @Test
    public void testFindByEmail() {
        Provider provider = new Provider();
        provider.setRuc(333444555L);
        provider.setEstate(true);
        provider.setRating(4.5f);
        provider.setComission(0.25);
        provider.setEmail("provider5@example.com");
        provider.setPhoneNumber("5556667777");

        // Guarda el proveedor
        providerRepository.save(provider);

        // Busca el proveedor por correo electrónico
        Provider foundProvider = providerRepository.findByEmail("provider5@example.com").orElse(null);

        // Verifica que el proveedor fue encontrado
        assertNotNull(foundProvider);
        assertEquals(provider.getEmail(), foundProvider.getEmail());
    }
}
