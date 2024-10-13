/*package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.dbp.winproyect.provider.domain.ProviderService;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoRequest;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.tag.domain.ServiceTag;
import com.dbp.winproyect.tag.domain.TagService;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;


import static com.dbp.winproyect.tag.domain.ServiceTag.ENTREGA;

@ExtendWith(MockitoExtension.class)
public class ServiceEntityServiceTests {
    @InjectMocks
    private ServiceEntityService serviceEntityService;

    @Mock
    private ServiceEntityRepository serviceEntityRepository;

    @Mock
    private ProviderService providerService;

    @Mock
    private TagService tagService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private EnterpriseRepository enterpriseRepository;

    @Mock
    private ModelMapper modelMapper;

    // Inicialización antes de cada test
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    // Test para obtener servicios por tag
    @Test
    public void testObtenerServiciosPorTag() {
        // Crear un tag de prueba
        ServiceTag tag = ENTREGA;

        // Crear un servicio de prueba
        ServiceEntity service1 = new ServiceEntity();
        service1.setName("Servicio 1");
        //service1.setTags(Set.of(tag)); // Asegúrate de que el tag está asociado al servicio

        // Configurar el mock para devolver el servicio cuando se busca por el tag
        List<ServiceEntity> services = List.of(service1);
        Mockito.when(serviceEntityRepository.findAllByTags_Name(ENTREGA)).thenReturn(services);

        // Configurar el mapeo del DTO
        ServiceDtoResponse dtoResponse = new ServiceDtoResponse();
        dtoResponse.setName("Servicio 1");
        Mockito.when(modelMapper.map(Mockito.any(ServiceEntity.class), Mockito.eq(ServiceDtoResponse.class)))
                .thenReturn(dtoResponse);

        // Ejecutar el método
        List<ServiceDtoResponse> result = serviceEntityService.obtenerServiciosPorTag(tag);

        // Verificar resultados
        Assertions.assertEquals(1, result.size());
        Assertions.assertEquals("Servicio 1", result.get(0).getName());
        Mockito.verify(serviceEntityRepository).findAllByTags_Name(ENTREGA);
    }


    // Test para crear un servicio
    @Test
    public void testCreateService() {
        // Mock de datos
        ServiceDtoRequest dtoRequest = new ServiceDtoRequest();
        dtoRequest.setName("Nuevo Servicio");
        dtoRequest.setDescription("Descripción de servicio");
        dtoRequest.setAddress("Calle 123");
        dtoRequest.setSuggestedPrice(100.0);

        Freelancer freelancer = new Freelancer();
        Mockito.when(freelancerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(freelancer));

        ServiceEntity savedEntity = new ServiceEntity();
        savedEntity.setName("Nuevo Servicio");
        Mockito.when(serviceEntityRepository.save(Mockito.any(ServiceEntity.class))).thenReturn(savedEntity);

        // Ejecutar el método
        ServiceEntity result = serviceEntityService.createService(dtoRequest);

        // Verificar resultados
        Assertions.assertEquals("Nuevo Servicio", result.getName());
        Mockito.verify(serviceEntityRepository).save(Mockito.any(ServiceEntity.class));
    }

    // Test para obtener un servicio por ID
    @Test
    public void testObtenerServicio() {
        Long id = 1L;
        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setName("Servicio Test ");

        when(serviceEntityRepository.findById(id)).thenReturn(Optional.of(serviceEntity));

        ServiceDtoResponse response = new ServiceDtoResponse();
        response.setName("Servicio Test");
        when(modelMapper.map(any(ServiceEntity.class), eq(ServiceDtoResponse.class)))
                .thenReturn(response);

        // Ejecutar el método
        ServiceDtoResponse result = serviceEntityService.obtenerServicio(id, 0, 10, "name");

        // Verificar resultados
        assertEquals("Servicio Test", result.getName());
        verify(serviceEntityRepository).findById(id);
    }

    // Test para eliminar un servicio
    @org.junit.Test
    @Test
    public void testDeleteService() {
        Long id = 1L;
        ServiceEntity serviceEntity = new ServiceEntity();

        Mockito.when(serviceEntityRepository.findById(id)).thenReturn(Optional.of(serviceEntity));

        // Ejecutar el método
        serviceEntityService.deleteService(id);

        // Verificar que el servicio fue eliminado
        Mockito.verify(serviceEntityRepository).delete(serviceEntity);
        System.out.println("El servicio con ID " + id + " fue eliminado.");
    }

}
*/

