package com.dbp.winproyect.provider.infrastructure;
import com.dbp.winproyect.AbstractContainerBaseTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.dbp.winproyect.provider.domain.*;
import com.dbp.winproyect.client.domain.*;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProviderRepositoryTest extends AbstractContainerBaseTest {
    @Autowired
    ProviderRepository repository;

    @Autowired
    TestEntityManager entityManager;

    Provider provider1;
    Provider provider2;
    Provider provider3;
    Client client1;
    Client client2;
    Client client3;

    @BeforeEach
    public void setUp() {
        client1 = new Client();
        client1.setFirstName("Client 1");
        client1.setLastName("Lastname 1");
        client1.setDni(123456);
        client1.setEmail("client1@test.com");
        client1.setShowAds(true);

        provider1 = new Provider();
        provider1.setFirstName("Provider 1");
        provider1.setLastName("Lastname 1");
        provider1.setEmail("provider1@test.com");
        provider1.setEstate(true);
        provider1.setRating(4.5F);

        provider2 = new Provider();
        provider2.setFirstName("Provider 2");
        provider2.setLastName("Lastname 2");
        provider2.setEmail("provider2@test.com");
        provider2.setEstate(false);
        provider2.setRating(3.8f);

        // Persistir los objetos en la base de datos
        entityManager.persist(client1);
        entityManager.persist(provider1);
        entityManager.persist(provider2);
        entityManager.flush();
    }

    @Test
    public void testFindByEmail() {
        Provider foundProvider = (Provider) repository.findByEmail("provider1@test.com").orElse(null);
        assertNotNull(foundProvider);
        assertEquals("Provider 1", foundProvider.getFirstName());
    }

    @Test
    public void testFindAllProviders() {
        List<Provider> providers = repository.findAll();
        assertEquals(2, providers.size());
    }

    @Test
    public void testDeleteProvider() {
        // Prueba para eliminar un proveedor
        Provider providerToDelete = (Provider) repository.findByEmail("provider2@test.com").orElse(null);
        assertNotNull(providerToDelete);

        repository.delete(providerToDelete);
        entityManager.flush();

        Provider deletedProvider = (Provider) repository.findByEmail("provider2@test.com").orElse(null);
        assertNull(deletedProvider);
    }
}
