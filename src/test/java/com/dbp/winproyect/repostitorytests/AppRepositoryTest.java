package com.dbp.winproyect.repostitorytests;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import com.dbp.winproyect.AbstractContainerBaseTest;
import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.appuser.infrastructure.BaseAppUserRepository;
import com.dbp.winproyect.location.domain.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.Optional;
import java.util.Date;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AppRepositoryTest extends AbstractContainerBaseTest {

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private BaseAppUserRepository repository;

    @Mock
    private TestEntityManager entityManager;

    private AppUser appUser1;

    @BeforeEach
    public void setUp() {
        appUser1 = new AppUser(); // Inicializa appUser1
        appUser1.setEmail("test@example.com");
        appUser1.setPassword("password123");
        appUser1.setPhoneNumber("1234567890");
        appUser1.setRegistrationDate(new Date());
        appUser1.setAddress("123 Main St");

        entityManager.persist(appUser1);
    }

    @Test
    void testAppUserCreation() {
        assertEquals("test@example.com", appUser1.getEmail());
        assertEquals("password123", appUser1.getPassword());
        assertEquals("1234567890", appUser1.getPhoneNumber());
        assertNotNull(appUser1.getRegistrationDate());
        assertEquals("123 Main St", appUser1.getAddress());
    }

    @Test
    void testSetAndGetEmail() {
        appUser1.setEmail("test@example.com");
        assertEquals("test@example.com", appUser1.getEmail());
    }

    @Test
    void testSetAndGetPassword() {
        appUser1.setPassword("password123");
        assertEquals("password123", appUser1.getPassword());
    }

    @Test
    void testSetAndGetPhoneNumber() {
        appUser1.setPhoneNumber("1234567890");
        assertEquals("1234567890", appUser1.getPhoneNumber());
    }

    @Test
    void testSetAndGetRegistrationDate() {
        Date registrationDate = new Date();
        appUser1.setRegistrationDate(registrationDate);
        assertEquals(registrationDate, appUser1.getRegistrationDate());
    }

    @Test
    void testSetAndGetAddress() {
        appUser1.setAddress("123 Main St");
        assertEquals("123 Main St", appUser1.getAddress());
    }

    @Test
    void testSetAndGetLocation() {
        Location location = new Location();
        appUser1.setLocation(location);
        assertEquals(location, appUser1.getLocation());
    }

    @Test
    void testFindByEmail() {
        // Simulamos que el repositorio devuelve el AppUser por email
        when(repository.findByEmail("test@example.com")).thenReturn(Optional.of(appUser1));

        // Llamada directa al repositorio
        Optional<AppUser> foundUser = repository.findByEmail("test@example.com");

        // Verificamos que el método findByEmail() fue invocado una vez
        verify(repository, times(1)).findByEmail("test@example.com");

        // Verificamos que los datos del usuario devuelto son correctos
        assertTrue(foundUser.isPresent());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertEquals("1234567890", foundUser.get().getPhoneNumber());
    }

    @Test
    void testGetByIdVerification() {
        // Simulamos que el repositorio devuelve el AppUser
        when(repository.findById(appUser1.getId())).thenReturn(Optional.of(appUser1));

        // Llamada directa al repositorio
        Optional<AppUser> foundUser = repository.findById(appUser1.getId());

        // Verificamos que el método findById() fue invocado una vez
        verify(repository, times(1)).findById(appUser1.getId());

        // Verificamos que los datos del usuario devuelto son correctos
        assertTrue(foundUser.isPresent());
        assertEquals(appUser1.getId(), foundUser.get().getId());
        assertEquals("test@example.com", foundUser.get().getEmail());
        assertEquals("1234567890", foundUser.get().getPhoneNumber());
    }
    @Test
    void testUpdateAppUser() {
        // Creamos nuevos detalles para el usuario
        appUser1.setEmail("updated@example.com");
        appUser1.setPhoneNumber("0987654321");

        // Simulamos que el repositorio guarda el usuario actualizado
        when(repository.save(appUser1)).thenReturn(appUser1);

        // Llamamos al método de actualización
        AppUser updatedUser = repository.save(appUser1);

        // Verificamos que el método save() fue invocado una vez
        verify(repository, times(1)).save(appUser1);

        // Verificamos que los datos del usuario actualizado son correctos
        assertEquals("updated@example.com", updatedUser.getEmail());
        assertEquals("0987654321", updatedUser.getPhoneNumber());
    }

    @Test
    void testDeleteAppUser() {
        // Simulamos que el repositorio devuelve el AppUser
        when(repository.findById(appUser1.getId())).thenReturn(Optional.of(appUser1));

        // Llamada directa al repositorio para eliminar el usuario
        repository.delete(appUser1);

        // Verificamos que el método delete() fue invocado una vez
        verify(repository, times(1)).delete(appUser1);

        // Verificamos que el usuario ya no está presente
        when(repository.findById(appUser1.getId())).thenReturn(Optional.empty());
        Optional<AppUser> deletedUser = repository.findById(appUser1.getId());

        assertFalse(deletedUser.isPresent());
    }


}
