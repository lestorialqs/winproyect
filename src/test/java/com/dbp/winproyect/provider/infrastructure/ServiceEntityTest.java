package com.dbp.winproyect.provider.infrastructure;

import com.dbp.winproyect.AbstractContainerBaseTest;
import org.assertj.core.api.Assertions;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.dbp.winproyect.provider.domain.Provider;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceEntityTest extends AbstractContainerBaseTest {

    @Mock
    private ServiceEntityRepository serviceEntityRepository;

    private ServiceEntity serviceEntity;
    private ServiceEntity serviceEntity2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks

        Provider provider = new Provider();  // Inicializa el primer proveedor
        Provider provider2 = new Provider(); // Inicializa el segundo proveedor

        // Creación de la primera entidad de servicio
        serviceEntity = new ServiceEntity();
        serviceEntity.setName("Plumbing Service");
        serviceEntity.setDescription("Provides high-quality plumbing services.");
        serviceEntity.setAddress("123 Main St");
        serviceEntity.setSuggestedPrice(100.0);
        serviceEntity.setAvg_rating(4.5f);
        serviceEntity.setProvider(provider);

        // Creación de la segunda entidad de servicio
        serviceEntity2 = new ServiceEntity();
        serviceEntity2.setName("Food Service");
        serviceEntity2.setDescription("Food is delicious.");
        serviceEntity2.setAddress("321 Main St");
        serviceEntity2.setSuggestedPrice(15.0);
        serviceEntity2.setAvg_rating(4.5f);
        serviceEntity2.setProvider(provider2);
        // Simula que el método save() devuelve una entidad con un ID
        when(serviceEntityRepository.save(any(ServiceEntity.class))).thenAnswer(invocation -> {
            ServiceEntity entity = invocation.getArgument(0);
            entity.setId(1L);  // Simula que la entidad tiene un ID asignado
            return entity;
        });
    }

    @Test
    public void testServiceEntityCreation() {
        assertNotNull(serviceEntity.getProvider());  // Comprueba que el proveedor no sea null
        assertEquals("Plumbing Service", serviceEntity.getName());
        assertEquals("Provides high-quality plumbing services.", serviceEntity.getDescription());
        assertEquals("123 Main St", serviceEntity.getAddress());
        assertEquals(100.0, serviceEntity.getSuggestedPrice());
        assertEquals(4.5f, serviceEntity.getAvg_rating());
    }

    @Test
    public void testSaveServiceEntity() {
        serviceEntityRepository.save(serviceEntity);  // Guarda la entidad de servicio
        verify(serviceEntityRepository, times(1)).save(serviceEntity);  // Verifica que el método save fue llamado una vez
    }

    @Test
    public void testListOfProviderRepositoryIsNotNull() {
        when(serviceEntityRepository.findAll()).thenReturn(List.of(serviceEntity, serviceEntity2));  // Mockea la lista de entidades

        List<ServiceEntity> serviceEntityList = serviceEntityRepository.findAll();  // Llama a findAll
        Assertions.assertThat(serviceEntityList).isNotNull();  // Comprueba que la lista no sea null
        Assertions.assertThat(serviceEntityList.size()).isEqualTo(2);  // Verifica que haya 2 entidades en la lista
    }
    @Test
    public void testServiceEntityRepositoryFindById(){
        // Guarda la entidad
        serviceEntity = serviceEntityRepository.save(serviceEntity);

        // Verifica que la entidad tenga un ID asignado
        assertNotNull(serviceEntity.getId(), "ID should not be null after saving the entity");

        // Simula la búsqueda por ID
        when(serviceEntityRepository.findById(serviceEntity.getId())).thenReturn(Optional.of(serviceEntity));

        // Encuentra la entidad por su ID
        Optional<ServiceEntity> serviceEntityOptional = serviceEntityRepository.findById(serviceEntity.getId());

        if (serviceEntityOptional.isPresent()) {
            ServiceEntity foundEntity = serviceEntityOptional.get();
            assertNotNull(foundEntity);  // Verifica que la entidad se encuentre
        } else {
            fail("ServiceEntity with id " + serviceEntity.getId() + " not found");
        }
    }
    @Test
    public void testFindByName() {
        // Simula la búsqueda por nombre
        when(serviceEntityRepository.findByName(serviceEntity.getName())).thenReturn(Optional.of(serviceEntity));

        // Llama al método findByName
        Optional<ServiceEntity> foundEntityOptional = serviceEntityRepository.findByName(serviceEntity.getName());

        // Verifica si la entidad fue encontrada
        assertTrue(foundEntityOptional.isPresent(), "ServiceEntity should be found by name");

        // Comprueba los valores de la entidad encontrada
        ServiceEntity foundEntity = foundEntityOptional.get();
        assertEquals(serviceEntity.getName(), foundEntity.getName());
        assertEquals(serviceEntity.getDescription(), foundEntity.getDescription());
    }
    @Test
    public void testDeleteById() {
        // Guarda la entidad en el repositorio (simulado)
        serviceEntityRepository.save(serviceEntity);

        // Simula la eliminación de la entidad
        doNothing().when(serviceEntityRepository).deleteById(serviceEntity.getId());

        // Llama al método deleteById
        serviceEntityRepository.deleteById(serviceEntity.getId());

        // Verifica que el método deleteById fue llamado exactamente una vez con el ID correcto
        verify(serviceEntityRepository, times(1)).deleteById(serviceEntity.getId());
    }
}
