package com.dbp.winproyect.auth.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class DniValidationService {

    @Autowired
    private WebClient.Builder webClientBuilder;

    private static final String RENIEC_API_URL = "https://api.apis.net.pe/v2/reniec/dni?numero=";
    private static final String SUNAT_API_FULL_URL = "https://api.apis.net.pe/v2/sunat/ruc/full?numero=";

    public Mono<Boolean> validateDni(String dni, String token) {
        return webClientBuilder.build()
                .get()
                .uri(RENIEC_API_URL + dni)
                .header("Authorization", "Bearer " + token)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(String.class)
                .map(response -> {
                    System.out.println(response);
                    return !response.contains("dni no valido");
                });
    }

}