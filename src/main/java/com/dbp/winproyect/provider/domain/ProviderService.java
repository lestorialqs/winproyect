package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.enterprise.domain.Enterprise;

import com.dbp.winproyect.appuser.domain.Role;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dbp.winproyect.enterprise.domain.Enterprise;

@Service
public class ProviderService {
    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private FreelancerRepository freelancerRepository;



    public String getProviderName(Provider provider) {
        // Dependiendo del rol, busca el nombre del proveedor
        if (provider.getRole() == Role.ENTERPRISE) {
            return enterpriseRepository.findById(provider.getId())
                    .map(Enterprise::getName)
                    .orElse("Nombre no disponible"); // Si no se encuentra, maneja el caso
        } else if (provider.getRole() == Role.FREELANCE) {
            return freelancerRepository.findById(provider.getId())
                    .map(freelancer -> freelancer.getFirstName() + " " + freelancer.getLastName())
                    .orElse("Nombre no disponible");
        }
        return "Proveedor no identificado"; // Caso por defecto
    }
}



