package com.dbp.winproyect.serviceEntity.application;


import com.dbp.winproyect.review.domain.Review;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoRequest;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import com.dbp.winproyect.tag.domain.ServiceTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/service")
public class ServiceEntityController {
    @Autowired
    ServiceEntityService serviceEntityService;
    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @PostMapping()
    public ResponseEntity<Void> createdService(@RequestBody ServiceDtoRequest serviceDtoRequest) {
        var service = serviceEntityService.createService(serviceDtoRequest);
        URI uri = URI.create("/service/" + service.getId());

        return ResponseEntity.created(uri).build();
    }
    @GetMapping("/{id}")
    public ResponseEntity<ServiceDtoResponse> getServiceById(@PathVariable("id") Long id,
                    @RequestParam(value = "pageNo", defaultValue = "0", required = false) Integer pageNo,
                    @RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
                    @RequestParam(value = "sort", defaultValue = "rating", required = false) String sort){
        ServiceDtoResponse response = serviceEntityService.obtenerServicio(id, pageNo, pageSize, sort);
        return ResponseEntity.ok(response);

    }
    @GetMapping()
    public ResponseEntity<List<ServiceDtoResponse>> getAllServices() {

        return ResponseEntity.ok(serviceEntityService.obtenerTodosLosServicios());
    }
    @GetMapping("/tag")
    public ResponseEntity<List<ServiceDtoResponse>> getAllServicesByTag(@RequestParam("tag") String tagName) {
        ServiceTag tagEnum;
        try {
            tagEnum = ServiceTag.valueOf(tagName.toUpperCase()); // Convierte a enum
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // Si el tag no existe en el enum, devuelve un error
        }

        List<ServiceDtoResponse> services = serviceEntityService.obtenerServiciosPorTag(tagEnum);
        return ResponseEntity.ok(services);
    }
    @GetMapping("/hola")
    public ResponseEntity<String> hola() {
        return ResponseEntity.ok("Hola");
    }

}
