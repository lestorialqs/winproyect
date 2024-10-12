package com.dbp.winproyect.auth.application;

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


@RestController
@RequestMapping("/auth")

public class AuthController {

    @Autowired
    private AuthService authService;



    // Registro para Cliente
    @PostMapping("/register/client")
    public ResponseEntity<String> registerClient(@RequestBody ClientDtoRegister clientDtoRegister) {
        try {
            String token = authService.registerClient(clientDtoRegister);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    // Registro para Empresa
    @PostMapping("/register/enterprise")
    public ResponseEntity<String> registerEnterprise(@RequestBody EnterpriseDtoRegister enterpriseDtoRegister) {
        try {
            String token = authService.registerEnterprise(enterpriseDtoRegister);
            return ResponseEntity.ok(token);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
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