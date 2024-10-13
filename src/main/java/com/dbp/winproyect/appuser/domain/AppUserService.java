package com.dbp.winproyect.appuser.domain;

import com.dbp.winproyect.appuser.update.ClientUpdateDto;
import com.dbp.winproyect.appuser.update.EnterpriseUpdateDto;
import com.dbp.winproyect.appuser.update.FreelancerUpdateDto;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.client.dto.ClientDtoViewPerfilResponse;
import com.dbp.winproyect.client.infrastructure.ClientRepository;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.enterprise.dto.EnterpriseDtoViewPerfilResponse;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import com.dbp.winproyect.freelancer.dto.FreelancerDtoViewPerfilResponse;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;



@Service
public class AppUserService {

    @Autowired
    private FreelancerRepository freelancerRepository;

    @Autowired
    private EnterpriseRepository enterpriseRepository;

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ModelMapper modelMapper;

    public Object getProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (role.contains("FREELANCER")) {
            Freelancer freelancer = freelancerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));
            FreelancerDtoViewPerfilResponse freelancerDtoViewPerfilResponse = new FreelancerDtoViewPerfilResponse();
            modelMapper.map( freelancer, freelancerDtoViewPerfilResponse );
            return freelancerDtoViewPerfilResponse;
        } else if (role.contains("ENTERPRISE")) {
            Enterprise enterprise = enterpriseRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Enterprise no encontrado"));
            EnterpriseDtoViewPerfilResponse enterpriseDtoViewPerfilResponse = new EnterpriseDtoViewPerfilResponse();
            modelMapper.map( enterprise, enterpriseDtoViewPerfilResponse );
            return enterpriseDtoViewPerfilResponse;
        } else if (role.contains("CLIENT")) {
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Client no encontrado"));
            ClientDtoViewPerfilResponse clientDtoViewPerfilResponse = new ClientDtoViewPerfilResponse();
            modelMapper.map( client, clientDtoViewPerfilResponse );
            return clientDtoViewPerfilResponse;
        }
        throw new RuntimeException("Rol no permitido");
    }
    public Object updateProfile(Map<String, Object> profileUpdateRequest) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();

        if (role.contains("FREELANCER")) {
            Freelancer freelancer = freelancerRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Freelancer no encontrado"));

            FreelancerUpdateDto freelancerDto = mapToDto(profileUpdateRequest, FreelancerUpdateDto.class);
            updateFreelancerFields(freelancer, freelancerDto);

            return freelancerRepository.save(freelancer);
        } else if (role.contains("ENTERPRISE")) {
            Enterprise enterprise = enterpriseRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Enterprise no encontrado"));

            EnterpriseUpdateDto enterpriseDto = mapToDto(profileUpdateRequest, EnterpriseUpdateDto.class);
            updateEnterpriseFields(enterprise, enterpriseDto);

            return enterpriseRepository.save(enterprise);
        } else if (role.contains("CLIENT")) {
            Client client = clientRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("Client no encontrado"));

            ClientUpdateDto clientDto = mapToDto(profileUpdateRequest, ClientUpdateDto.class);
            updateClientFields(client, clientDto);

            return clientRepository.save(client);
        }
        throw new RuntimeException("Rol no permitido");
    }

    private <T> T mapToDto(Map<String, Object> requestBody, Class<T> dtoClass) {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.convertValue(requestBody, dtoClass);
    }


    //division en funciones:

    private void updateFreelancerFields(Freelancer freelancer, FreelancerUpdateDto freelancerDto) {
        freelancer.setFirstName(freelancerDto.getFirstName());
        freelancer.setLastName(freelancerDto.getLastName());
        freelancer.setAge(freelancerDto.getAge());
        freelancer.setDni(freelancerDto.getDni());
        freelancer.setExperience(freelancerDto.getExperience());
        freelancer.setLevelEducation(freelancerDto.getLevelEducation());
    }

    private void updateEnterpriseFields(Enterprise enterprise, EnterpriseUpdateDto enterpriseDto) {
        enterprise.setName(enterpriseDto.getName());
        enterprise.setDescription(enterpriseDto.getDescription());
        enterprise.setBusinessSector(enterpriseDto.getBusinessSector());
        enterprise.setSize(enterpriseDto.getSize());
        enterprise.setAddress(enterpriseDto.getAddress());
    }

    private void updateClientFields(Client client, ClientUpdateDto clientDto) {
        client.setFirstName(clientDto.getFirstName());
        client.setLastName(clientDto.getLastName());
        client.setDni(clientDto.getDni());
        client.setShowAds(clientDto.getShowAds());
    }
}
