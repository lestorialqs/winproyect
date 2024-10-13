package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.enterprise.domain.BusinessSector;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.enterprise.domain.EnterpriseService;
import com.dbp.winproyect.enterprise.domain.Size;
import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.util.ReflectionUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EnterpriseServiceTests {

    @Mock
    private EnterpriseRepository enterpriseRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private EnterpriseService enterpriseService;

    private Enterprise enterprise;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Inicializar una empresa de prueba
        enterprise = new Enterprise();
        enterprise.setId(1L); // Asumimos que hereda de `Provider` que tiene un campo `id`
        enterprise.setRuc(123456789L);
        enterprise.setName("Tech Corp");
        enterprise.setDescription("A tech company");
        enterprise.setBusinessSector(BusinessSector.TECHNOLOGY);
        enterprise.setSize(Size.MEDIANA);
        enterprise.setAddress("123 Tech Street");
    }

    @Test
    void testObtenerPerfilEmpresa_Success() {
        // Simular la búsqueda de la empresa por ID
        when(enterpriseRepository.findById(1L)).thenReturn(Optional.of(enterprise));

        // Simular la conversión a DTO
        EnterpriseDtoViewPerfilResponse expectedDto = new EnterpriseDtoViewPerfilResponse();
        //expectedDto.setName(enterprise.getName());
        expectedDto.setDescription(enterprise.getDescription());
        when(modelMapper.map(enterprise, EnterpriseDtoViewPerfilResponse.class)).thenReturn(expectedDto);

        // Ejecutar el método a probar
        EnterpriseDtoViewPerfilResponse result = enterpriseService.obtenerPerfilEmpresa(1L);

        // Verificar que el resultado no es nulo
        assertNotNull(result);
        //ssertEquals("Tech Corp", result.getName());
        assertEquals("A tech company", result.getDescription());

        // Verificar que el repositorio fue llamado una vez
        verify(enterpriseRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPerfilEmpresa_NotFound() {
        // Simular que la empresa no se encuentra
        when(enterpriseRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción de ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> enterpriseService.obtenerPerfilEmpresa(1L));

        // Verificar que el repositorio fue llamado una vez
        verify(enterpriseRepository, times(1)).findById(1L);
    }

    @Test
    void testActualizarPerfilEmpresa_Success() {
        // Simular la búsqueda de la empresa por ID
        when(enterpriseRepository.findById(1L)).thenReturn(Optional.of(enterprise));

        // Preparar las actualizaciones en un Map
        Map<String, Object> updates = new HashMap<>();
        updates.put("name", "New Tech Corp");
        updates.put("description", "A leading tech company");

        // Ejecutar el método de actualización
        EnterpriseDtoViewPerfilResponse expectedDto = new EnterpriseDtoViewPerfilResponse();
        //expectedDto.setName("New Tech Corp");
        expectedDto.setDescription("A leading tech company");
        when(enterpriseRepository.save(any(Enterprise.class))).thenReturn(enterprise);
        when(modelMapper.map(enterprise, EnterpriseDtoViewPerfilResponse.class)).thenReturn(expectedDto);

        EnterpriseDtoViewPerfilResponse result = enterpriseService.actualizarPerfilEmpresa(1L, updates);

        // Verificar que los campos de la empresa fueron actualizados correctamente
        assertEquals("New Tech Corp", enterprise.getName());
        assertEquals("A leading tech company", enterprise.getDescription());

        // Verificar que se guardaron los cambios
        verify(enterpriseRepository, times(1)).save(enterprise);
    }

    @Test
    void testActualizarPerfilEmpresa_NotFound() {
        // Simular que la empresa no se encuentra
        when(enterpriseRepository.findById(1L)).thenReturn(Optional.empty());

        // Preparar las actualizaciones en un Map
        Map<String, Object> updates = new HashMap<>();

        // Verificar que se lanza una excepción de ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> enterpriseService.actualizarPerfilEmpresa(1L, updates));

        // Verificar que el repositorio fue llamado una vez
        verify(enterpriseRepository, times(1)).findById(1L);
    }
}
