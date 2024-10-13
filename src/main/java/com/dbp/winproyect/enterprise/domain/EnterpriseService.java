package com.dbp.winproyect.enterprise.domain;
import org.springframework.util.ReflectionUtils;  // Importa esta línea

import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;

import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class EnterpriseService {
    private final EnterpriseRepository enterpriseRepository;
    private final ModelMapper modelMapper;


    public boolean existsById(Long freelancerId) {
        return enterpriseRepository.existsById(freelancerId);
    }

    // Método para obtener el perfil de una empresa
    public EnterpriseDtoViewPerfilResponse obtenerPerfilEmpresa(Long enterpriseId) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));

        return modelMapper.map(enterprise, EnterpriseDtoViewPerfilResponse.class);
    }

    // Método para actualizar el perfil de una empresa
    public EnterpriseDtoViewPerfilResponse actualizarPerfilEmpresa(Long enterpriseId, Map<String, Object> updates) {
        Enterprise enterprise = enterpriseRepository.findById(enterpriseId)
                .orElseThrow(() -> new ResourceNotFoundException("Empresa no encontrada"));

        // Aplicar actualizaciones al objeto empresa
        updates.forEach((key, value) -> {
            Field field = ReflectionUtils.findField(Enterprise.class, key);
            if (field != null) {
                field.setAccessible(true);
                ReflectionUtils.setField(field, enterprise, value);
            }
        });

        Enterprise updatedEnterprise = enterpriseRepository.save(enterprise);
        return modelMapper.map(updatedEnterprise, EnterpriseDtoViewPerfilResponse.class);
    }
}