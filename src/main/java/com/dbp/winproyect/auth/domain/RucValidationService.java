package com.dbp.winproyect.auth.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class RucValidationService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String RENIEC_API_URL = "https://api.apis.net.pe/v2/sunat/ruc?numero=";
    private static final String SUNAT_API_FULL_URL = "https://api.apis.net.pe/v2/sunat/ruc/full?numero=";

    public Mono<Boolean> validateRuc(String ruc, String token) {
        return webClientBuilder.build()
                .get()
                .uri(RENIEC_API_URL + ruc)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    return !response.contains("error");
                });
    }

    public Mono<String> getFullRucInfo(String ruc, String token) {
        return webClientBuilder.build()
                .get()
                .uri(SUNAT_API_FULL_URL + ruc)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class);
    }
}