package com.dbp.winproyect.serviceEntity.domain;

import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ServiceEntityService {

    private final ServiceEntityRepository serviceEntityRepository;



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
    public ServiceEntity obtenerServicio(Long id) { //retornar dto

        ServiceEntity service = serviceEntityRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("No se encontro el servicio"));

        return service;
    }

    public void deleteService(Long id) {

        serviceEntityRepository.deleteById(id);


    }


}
