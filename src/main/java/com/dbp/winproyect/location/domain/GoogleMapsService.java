package com.dbp.winproyect.location.domain;


import com.dbp.winproyect.location.infraestructure.LocationRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class GoogleMapsService {
    @Autowired
    private LocationRepository locationRepository;
    @Value("${google.maps.api.key}")  // Se inyecta la clave API desde application.properties
    private String apiKey;
    private final ObjectMapper objectMapper = new ObjectMapper();

    private final RestTemplate restTemplate = new RestTemplate();

    public Location getCoordinates(String address) {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?address=" + address + "&key=" + apiKey;
        String response = restTemplate.getForObject(url, String.class);

        try {
            JsonNode root = objectMapper.readTree(response);
            JsonNode location = root.path("results").get(0).path("geometry").path("location");

            double latitude = location.path("lat").asDouble();  // Extraer como double
            double longitude = location.path("lng").asDouble();  // Extraer como double

            Location locationEntity = new Location();
            locationEntity.setAddress(address);
            locationEntity.setLatitude(latitude);
            locationEntity.setLongitude(longitude);

            return locationEntity;
        } catch (Exception e) {
            throw new RuntimeException("Error parsing response from Google Maps API", e);
        }
    }

    public Location getAndSaveLocation(String address) {
        Location location = getCoordinates(address);  // Usamos el método que creaste antes
        return locationRepository.save(location);  // Guardamos la ubicación en la base de datos
    }

}
