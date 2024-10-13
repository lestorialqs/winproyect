package com.dbp.winproyect.repostitorytests;

import static com.dbp.winproyect.enterprise.domain.Size.MEDIANA;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EnterpriseRepositoryTest {

    @Mock
    private ProviderRepository providerRepository; // Repositorio mock para Enterprise, que hereda de Provider

    @InjectMocks
    private Enterprise enterprise; // Objeto Enterprise que vamos a probar

    @BeforeEach
    public void setUp() {
        enterprise = new Enterprise();
        enterprise.setRuc(12345678901L);
        enterprise.setName("TechCorp");
        enterprise.setDescription("Tech solutions provider");
        enterprise.setBusinessSector(BusinessSector.TECHNOLOGY);
        enterprise.setSize(MEDIANA);
        enterprise.setAddress("123 Tech Street");
    }

    @Test
    void testEnterpriseCreation() {
        assertEquals(12345678901L, enterprise.getRuc());
        assertEquals("TechCorp", enterprise.getName());
        assertEquals("Tech solutions provider", enterprise.getDescription());
        assertEquals(BusinessSector.TECHNOLOGY, enterprise.getBusinessSector());
        assertEquals(MEDIANA, enterprise.getSize());
        assertEquals("123 Tech Street", enterprise.getAddress());
    }

    @Test
    void testFindByRuc() {
        // Simulamos que el repositorio devuelve la Enterprise por su RUC
        when(providerRepository.findByRuc(12345678901L)).thenReturn(Optional.of(enterprise));

        // Llamada directa al repositorio
        Optional<Enterprise> foundEnterprise = providerRepository.findByRuc(12345678901L);

        // Verificamos que el método findByRuc() fue invocado una vez
        verify(providerRepository, times(1)).findByRuc(12345678901L);

        // Verificamos que los datos de la empresa devuelta son correctos
        assertTrue(foundEnterprise.isPresent());
        assertEquals("TechCorp", foundEnterprise.get().getName());
        assertEquals("Tech solutions provider", foundEnterprise.get().getDescription());
    }

    @Test
    void testUpdateEnterprise() {
        // Simulamos una empresa existente
        Enterprise existingEnterprise = new Enterprise();
        existingEnterprise.setRuc(12345678901L);
        existingEnterprise.setName("TechCorp");
        existingEnterprise.setDescription("Tech solutions provider");

        // Simulamos que el repositorio devuelve la empresa existente
        when(providerRepository.findByRuc(12345678901L)).thenReturn(Optional.of(existingEnterprise));

        // Nuevos detalles para la empresa
        existingEnterprise.setName("NewTechCorp");
        existingEnterprise.setDescription("Innovative tech solutions");

        // Simulamos que el repositorio guarda la empresa actualizada
        when(providerRepository.save(any(Enterprise.class))).thenReturn(existingEnterprise);

        // Llamada directa para actualizar la empresa
        Enterprise updatedEnterprise = providerRepository.save(existingEnterprise);

        // Verificamos que el método save() fue invocado una vez
        verify(providerRepository, times(1)).save(existingEnterprise);

        // Verificamos que los detalles de la empresa se actualizaron correctamente
        assertEquals("NewTechCorp", updatedEnterprise.getName());
        assertEquals("Innovative tech solutions", updatedEnterprise.getDescription());
    }

    @Test
    void testDeleteEnterprise() {
        // Simulamos que el repositorio devuelve la Enterprise por su RUC
        when(providerRepository.findByRuc(12345678901L)).thenReturn(Optional.of(enterprise));

        // Llamada directa para eliminar la empresa
        providerRepository.delete(enterprise);

        // Verificamos que el método delete() fue invocado una vez
        verify(providerRepository, times(1)).delete(enterprise);

        // Verificamos que la empresa ya no está presente
        when(providerRepository.findByRuc(12345678901L)).thenReturn(Optional.empty());
        Optional<Enterprise> deletedEnterprise = providerRepository.findByRuc(12345678901L);

        assertFalse(deletedEnterprise.isPresent());
    }

    @Test
    void testInvalidRuc() {
        enterprise.setRuc(null);
        assertNull(enterprise.getRuc());

        enterprise.setRuc(-1L);
        assertTrue(enterprise.getRuc() < 0);
    }
}
