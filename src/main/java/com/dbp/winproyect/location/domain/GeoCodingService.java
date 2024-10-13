package com.dbp.winproyect.location.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class GeocodingService {

    @Value("${google.api.key}")
    private String googleApiKey;

    private final RestTemplate restTemplate = new RestTemplate();

    public Location getCoordinatesFromAddress(String address) {
        // Construir la URL para la API de Geocoding
        URI uri = UriComponentsBuilder.fromUriString("https://maps.googleapis.com/maps/api/geocode/json")
                .queryParam("address", address)
                .queryParam("key", googleApiKey)
                .build()
                .toUri();

        // Llamar a la API
        GeocodingResponse response = restTemplate.getForObject(uri, GeocodingResponse.class);

        if (response != null && !response.getResults().isEmpty()) {
            GeocodingResult result = response.getResults().get(0);
            Location location = new Location();
            location.setAddress(address);
            location.setLatitude(result.getGeometry().getLocation().getLat());
            location.setLongitude(result.getGeometry().getLocation().getLng());
            return location;
        }
        throw new RuntimeException("No se encontraron coordenadas para la dirección: " + address);
    }
}
