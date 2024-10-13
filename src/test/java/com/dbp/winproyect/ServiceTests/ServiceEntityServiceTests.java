package com.dbp.winproyect.ServiceTests;

import static com.dbp.winproyect.tag.domain.ServiceTag.LIMPIEZA;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.dbp.winproyect.provider.infrastructure.ProviderRepository;
import com.dbp.winproyect.review.domain.ReviewService;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoRequest;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.tag.domain.ServiceTag;
import com.dbp.winproyect.tag.domain.TagService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class ServiceEntityServiceTests {

    @InjectMocks
    private ServiceEntityService serviceEntityService;

    @Mock
    private ServiceEntityRepository serviceEntityRepository;

    @Mock
    private ProviderRepository providerRepository;

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private EnterpriseRepository enterpriseRepository;

    @Mock
    private TagService tagService;

    @Mock
    private ReviewService reviewService;

    @Mock
    private Authentication authentication;

    @BeforeEach
    void setUp() {
        // Crear y establecer el contexto de seguridad
        SecurityContext securityContext = mock(SecurityContext.class);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        SecurityContextHolder.setContext(securityContext);

        // Configurar el comportamiento del objeto de autenticación simulado
        when(authentication.getName()).thenReturn("test@example.com"); // Cambia este valor según tu caso de uso
    }

    @Test
    void testCreateService() {
        // Crear un DTO de solicitud
        ServiceDtoRequest serviceDtoRequest = new ServiceDtoRequest();
        serviceDtoRequest.setName("Test Service");
        serviceDtoRequest.setDescription("Test Description");
        serviceDtoRequest.setAddress("123 Test St");
        serviceDtoRequest.setSuggestedPrice(100.0);

        // Simular la respuesta del repositorio de proveedores
        when(providerRepository.findByEmail(anyString())).thenReturn(Optional.empty());
        // Simular la respuesta del repositorio de empresas
        when(enterpriseRepository.findByEmail(anyString())).thenReturn(Optional.of(new Enterprise()));

        // Llamar al método a probar
        ServiceEntity savedService = serviceEntityService.createService(serviceDtoRequest);

        // Verificar que el servicio fue guardado
        verify(serviceEntityRepository, times(1)).save(any(ServiceEntity.class));
        assertEquals("Test Service", savedService.getName());
    }

    @Test
    void testObtenerServiciosPorTag() {
        // Simular la respuesta del repositorio de servicios
        ServiceTag tag = LIMPIEZA; // Asume que tienes un objeto ServiceTag
        ServiceEntity service1 = new ServiceEntity();
        ServiceEntity service2 = new ServiceEntity();

        when(serviceEntityRepository.findAllByTags_Name(tag)).thenReturn(Arrays.asList(service1, service2));

        // Llamar al método a probar
        List<ServiceDtoResponse> services = serviceEntityService.obtenerServiciosPorTag(tag);

        // Verificar que se devolvieron los servicios esperados
        assertEquals(2, services.size());
        verify(serviceEntityRepository, times(1)).findAllByTags_Name(tag);
    }