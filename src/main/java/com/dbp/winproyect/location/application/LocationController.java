package com.dbp.winproyect.location.application;

import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.dbp.winproyect.location.domain.GoogleMapsService;
import com.dbp.winproyect.location.domain.Location;
import com.dbp.winproyect.location.dto.LocationResponseDto;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntity;
import com.dbp.winproyect.serviceEntity.domain.ServiceEntityService;
import com.dbp.winproyect.serviceEntity.infrastructure.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/location")
public class LocationController {

    @Autowired
    private GoogleMapsService googleMapsService;
    @Autowired
    private ServiceEntityService serviceEntityService;
    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @GetMapping("/getcoordinates")
    public ResponseEntity<LocationResponseDto> getCoordinates(@RequestBody String address) {

        Location location = googleMapsService.getCoordinates(address);
        LocationResponseDto responseDto = new LocationResponseDto(location.getLatitude(), location.getLongitude());
        responseDto.setLatitude(location.getLatitude());
        responseDto.setLongitude(location.getLongitude());
        responseDto.setFormattedAddress(location.getAddress());
        responseDto.setMessage("Coordinates fetched successfully");
        responseDto.setStatus("SUCCESS");

        // Devolver el DTO en la respuesta
        return ResponseEntity.ok(responseDto);
    }

    // Obtener las coordenadas de un servicio por su ID (basado en la dirección)
    @GetMapping("/service/{serviceId}")
    public ResponseEntity<LocationResponseDto> getServiceLocation(@PathVariable Long serviceId) {
        // Lógica para buscar el servicio y obtener su dirección
        ServiceEntity serviceEntity = serviceEntityRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("Servicio no encontrado"));

        // Obtener coordenadas basadas en la dirección del servicio
        Location location = googleMapsService.getCoordinates(serviceEntity.getAddress());
        LocationResponseDto responseDto = new LocationResponseDto(location.getLatitude(), location.getLongitude());
        responseDto.setLatitude(location.getLatitude());
        responseDto.setLongitude(location.getLongitude());
        responseDto.setFormattedAddress(location.getAddress());
        responseDto.setMessage("Coordinates fetched successfully");
        responseDto.setStatus("SUCCESS");

        // Devolver el DTO en la respuesta
        return ResponseEntity.ok(responseDto);
    }



}