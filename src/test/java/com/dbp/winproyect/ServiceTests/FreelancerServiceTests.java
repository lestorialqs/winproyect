package com.dbp.winproyect.ServiceTests;

import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import com.dbp.winproyect.freelancer.domain.FreelancerService;
import com.dbp.winproyect.freelancer.domain.LevelEducation;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class FreelancerServiceTests {

    @Mock
    private FreelancerRepository freelancerRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private FreelancerService freelancerService;

    private Freelancer freelancer;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Crear un freelancer de prueba
        freelancer = new Freelancer();
        freelancer.setId(1L); // Asumimos que hereda de `Provider` que tiene un campo `id`
        freelancer.setFirstName("John");
        freelancer.setLastName("Doe");
        freelancer.setAge(30);
        freelancer.setDni(12345678);
        freelancer.setExperience("5 years of experience");
        freelancer.setLevelEducation(LevelEducation.UNDERGRADUATE);
    }

    @Test
    void testObtenerPerfilFreelancer_Success() {
        // Simular la búsqueda del freelancer por ID
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        // Simular la conversión a DTO
        FreelancerDtoViewPerfilResponse expectedDto = new FreelancerDtoViewPerfilResponse();
        expectedDto.setFirstName(freelancer.getFirstName());
        expectedDto.setLastName(freelancer.getLastName());
        expectedDto.setDni((long) freelancer.getDni());
        expectedDto.setExperience(freelancer.getExperience());
        expectedDto.setLevelEducation(freelancer.getLevelEducation());
        when(modelMapper.map(freelancer, FreelancerDtoViewPerfilResponse.class)).thenReturn(expectedDto);

        // Ejecutar el método a probar
        FreelancerDtoViewPerfilResponse result = freelancerService.obtenerPerfilFreelancer(1L);

        // Verificar que el resultado no es nulo
        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        assertEquals("Doe", result.getLastName());
        assertEquals(12345678L, result.getDni());
        assertEquals("5 years of experience", result.getExperience());
        assertEquals(LevelEducation.UNDERGRADUATE, result.getLevelEducation());

        // Verificar que el repositorio fue llamado una vez
        verify(freelancerRepository, times(1)).findById(1L);
    }

    @Test
    void testObtenerPerfilFreelancer_NotFound() {
        // Simular que el freelancer no se encuentra
        when(freelancerRepository.findById(1L)).thenReturn(Optional.empty());

        // Verificar que se lanza una excepción de ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> freelancerService.obtenerPerfilFreelancer(1L));

        // Verificar que el repositorio fue llamado una vez
        verify(freelancerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFreelancer_Success() {
        // Simular la búsqueda del freelancer por ID
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        // Preparar las actualizaciones en un Map
        Map<String, Object> updates = new HashMap<>();
        updates.put("firstName", "Jane");
        updates.put("experience", "7 years of experience");

        // Ejecutar el método de actualización
        FreelancerDtoViewPerfilResponse expectedDto = new FreelancerDtoViewPerfilResponse();
        expectedDto.setFirstName("Jane");
        expectedDto.setExperience("7 years of experience");
        when(freelancerRepository.save(any(Freelancer.class))).thenReturn(freelancer);
        when(modelMapper.map(freelancer, FreelancerDtoViewPerfilResponse.class)).thenReturn(expectedDto);

        FreelancerDtoViewPerfilResponse result = freelancerService.updateFreelancer(1L, updates);

        // Verificar que los campos del freelancer fueron actualizados correctamente
        assertEquals("Jane", freelancer.getFirstName());
        assertEquals("7 years of experience", freelancer.getExperience());

        // Verificar que se guardaron los cambios
        verify(freelancerRepository, times(1)).save(freelancer);
    }

    @Test
    void testUpdateFreelancer_NotFound() {
        // Simular que el freelancer no se encuentra
        when(freelancerRepository.findById(1L)).thenReturn(Optional.empty());

        // Preparar las actualizaciones en un Map
        Map<String, Object> updates = new HashMap<>();

        // Verificar que se lanza una excepción de ResourceNotFoundException
        assertThrows(ResourceNotFoundException.class, () -> freelancerService.updateFreelancer(1L, updates));

        // Verificar que el repositorio fue llamado una vez
        verify(freelancerRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateFreelancer_InvalidField() {
        // Simular la búsqueda del freelancer por ID
        when(freelancerRepository.findById(1L)).thenReturn(Optional.of(freelancer));

        // Intentar actualizar un campo que no existe
        Map<String, Object> updates = new HashMap<>();
        updates.put("invalidField", "value");

        // Verificar que se lanza una excepción de IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> freelancerService.updateFreelancer(1L, updates));
    }
}
