package com.dbp.winproyect.serviceEntity.application;


import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.dto.ServiceDtoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service")
public class ServiceEntityController {
    @Autowired
    ServiceEntityService serviceEntityService;


    @GetMapping("/{id}")
    public ResponseEntity<ServiceDtoResponse> getServiceById(@PathVariable("id") Long id) {
        ServiceDtoResponse response = serviceEntityService.obtenerServicio(id);
        return ResponseEntity.ok(response);
    }
}
