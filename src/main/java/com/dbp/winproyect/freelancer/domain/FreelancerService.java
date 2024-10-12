package com.dbp.winproyect.freelancer.domain;

import com.dbp.winproyect.exeptions.ResourceNotFoundException;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FreelancerService {
    private final FreelancerRepository freelancerRepository;
    private final ModelMapper modelMapper;


    // Méetodo para obtener el perfil de un freelancer
    public FreelancerDtoViewPerfilResponse obtenerPerfilFreelancer(Long freelancerId) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer no encontrado"));

        return modelMapper.map(freelancer, FreelancerDtoViewPerfilResponse.class);
    }


    public FreelancerDtoViewPerfilResponse updateFreelancer(Long freelancerId, Map<String, Object> updates) {
        Freelancer freelancer = freelancerRepository.findById(freelancerId)
                .orElseThrow(() -> new ResourceNotFoundException("Freelancer no encontrado"));

        // Aplicar actualizaciones al objeto freelancer
        updates.forEach((key, value) -> {
            try {
                Field field = Freelancer.class.getDeclaredField(key);
                field.setAccessible(true);
                field.set(freelancer, value);
            } catch (NoSuchFieldException e) {
                // Manejar el caso donde el campo no existe
                throw new IllegalArgumentException("Campo no encontrado: " + key);
            } catch (IllegalAccessException e) {
                // Manejar el caso donde no se puede acceder al campo
                throw new RuntimeException("Error al acceder al campo: " + key);
            }
        });

        Freelancer updatedFreelancer = freelancerRepository.save(freelancer);
        return modelMapper.map(updatedFreelancer, FreelancerDtoViewPerfilResponse.class);
    }
}