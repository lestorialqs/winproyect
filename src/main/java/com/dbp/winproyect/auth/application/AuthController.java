package com.dbp.winproyect.auth.application;

import com.dbp.winproyect.auth.domain.DniValidationService;
import com.dbp.winproyect.auth.domain.RucValidationService;
import com.dbp.winproyect.auth.dto.ClientDtoRegister;
import com.dbp.winproyect.auth.dto.EnterpriseDtoRegister;
import com.dbp.winproyect.auth.dto.FreelancerDtoRegister;
import com.dbp.winproyect.auth.dto.UserLoginDto;
import com.dbp.winproyect.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private RucValidationService rucValidationService;

    @Autowired
    private DniValidationService dniValidationService;

    String token = "apis-token-10962.XDdecgSy4vPRkfxbYMLnPNJ6YUAwfQid";
    // Registro para Cliente
    @PostMapping("/register/client")
    public ResponseEntity<String> registerClient(@RequestBody ClientDtoRegister clientDtoRegister) {
        // Obtener el token aquí antes de la validación

        // Validar el DNI
        boolean isDniValid = Boolean.TRUE.equals(dniValidationService.validateDni(String.valueOf(clientDtoRegister.getDni()), token).block());

        if (isDniValid) {
            try {
                // Registrar el cliente
                String clientToken = authService.registerClient(clientDtoRegister);
                return ResponseEntity.ok(clientToken);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
            }
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DNI inválido");
        }
    }


    @PostMapping("/register/enterprise")
    public Mono<ResponseEntity<String>> registerEnterprise(@RequestBody EnterpriseDtoRegister enterpriseDtoRegister) {


        return rucValidationService.validateRuc(String.valueOf(enterpriseDtoRegister.getRuc()), token)
                .flatMap(isRucValid -> {
                    if (isRucValid) {
                        try {
                            // Registrar la empresa
                            String enterpriseToken = authService.registerEnterprise(enterpriseDtoRegister);
                            return Mono.just(ResponseEntity.ok(enterpriseToken));
                        } catch (Exception e) {
                            return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage()));
                        }
                    } else {
                        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body("RUC inválido"));
                    }
                });
    }


    // Registro para Freelancer
    @PostMapping("/register/freelancer")
    public ResponseEntity<String> registerFreelancer(@RequestBody FreelancerDtoRegister freelancerDtoRegister) {
        try {
            String token = authService.registerFreelancer(freelancerDtoRegister);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDto userLoginDto) {
        try {
            String token = authService.login(userLoginDto.getEmail(), userLoginDto.getPassword());
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}