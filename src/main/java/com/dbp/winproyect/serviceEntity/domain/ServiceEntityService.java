package com.dbp.winproyect.serviceEntity.domain;

import com.dbp.winproyect.appuser.domain.Role;
import com.dbp.winproyect.enterprise.infrastructure.EnterpriseRepository;
import com.dbp.winproyect.freelancer.infrastructure.FreelancerRepository;
import com.dbp.winproyect.provider.infrastructure.ProviderRepository;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service

public class ServiceEntityService {


    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ServiceEntityRepository repository;
    @Autowired
    private ProviderRepository providerRepository;
    @Autowired
    private FreelancerRepository freelancerRepository;


    private final ServiceEntityRepository serviceEntityRepository;
    @Autowired
    private EnterpriseRepository enterpriseRepository;


    public ServiceEntityService( ServiceEntityRepository serviceEntityRepository) {
        this.serviceEntityRepository = serviceEntityRepository;
    }

    //Permite a un proveedor publicar un servicio.
    public void createService(ServiceEntity serviceEntity) {// dto

        serviceEntityRepository.save(serviceEntity);

    }
    //Obtener todos los servicios en paginas para no mandarlo todo defrente
    public Page<ServiceEntity> obtenerServiciosPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return serviceEntityRepository.findAll(pageable);
    }
    //obtener un servicio por id
    public ServiceDtoResponse obtenerServicio(Long id) { //retornar dto

        ServiceEntity service = serviceEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el servicio"));
        ServiceDtoResponse response = modelMapper.map(service, ServiceDtoResponse.class);
        if(service.getProvider().getRole() == Role.ENTERPRISE){
            response.setNameProvider(enterpriseRepository.findById(service.getProvider().getId()).get().getName());
        }

        return response;
    }
    public void deleteService(Long id){
        ServiceEntity service = serviceEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontró el servicio con id " + id));
        serviceEntityRepository.delete(service);
    }

    public void updateService(ServiceEntity serviceEntity, Long id) {//dto de patch, por id
        //pasar los atributos del dto a un serviceEntity
        //luego guardar:

        serviceEntityRepository.save(serviceEntity);
    }
}
