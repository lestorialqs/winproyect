package com.dbp.winproyect.auth.service;


import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.appuser.infrastructure.BaseAppUserRepository;
import com.dbp.winproyect.auth.domain.AppUserDetails;
import com.dbp.winproyect.auth.dto.ClientDtoRegister;
import com.dbp.winproyect.auth.dto.EnterpriseDtoRegister;
import com.dbp.winproyect.auth.dto.FreelancerDtoRegister;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.freelancer.domain.Freelancer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private BaseAppUserRepository baseAppUserRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;  // Verifica que este campo esté correctamente inyectado

    @Autowired
    private JwtService jwtService;

    public String login(String email, String password) throws Exception {
        AppUser appUser = baseAppUserRepository.findByEmail(email)
                .orElseThrow(() -> new Exception("Usuario no encontrado"));

        if (passwordEncoder.matches(password, appUser.getPassword())) {
            return jwtService.generateToken(new AppUserDetails(appUser));
        } else {
            throw new Exception("Contraseña incorrecta");
        }
    }
    public String registerClient(ClientDtoRegister clientDtoRegister) throws Exception {
        if (baseAppUserRepository.findByEmail(clientDtoRegister.getEmail()).isPresent()) {
            throw new Exception("El correo ya está en uso");
        }

        // Crear un nuevo Cliente a partir del DTO
        Client client = new Client();
        client.setEmail(clientDtoRegister.getEmail());
        client.setPassword(passwordEncoder.encode(clientDtoRegister.getPassword()));
        client.setPhoneNumber(clientDtoRegister.getPhoneNumber());
        client.setAddress(clientDtoRegister.getAddress());
        client.setFirstName(clientDtoRegister.getFirstName());
        client.setLastName(clientDtoRegister.getLastName());

        // Guardar el cliente y generar el token JWT
        baseAppUserRepository.save(client);
        return jwtService.generateToken(new AppUserDetails(client));
    }

    // Método para registrar una Empresa
    public String registerEnterprise(EnterpriseDtoRegister enterpriseDtoRegister) throws Exception {
        if (baseAppUserRepository.findByEmail(enterpriseDtoRegister.getEmail()).isPresent()) {
            throw new Exception("El correo ya está en uso");
        }

        // Crear una nueva Empresa a partir del DTO
        Enterprise enterprise = new Enterprise();
        enterprise.setEmail(enterpriseDtoRegister.getEmail());
        enterprise.setPassword(passwordEncoder.encode(enterpriseDtoRegister.getPassword()));
        enterprise.setRuc(enterpriseDtoRegister.getRuc());
        enterprise.setBusinessSector(enterpriseDtoRegister.getBusinessSector());
        enterprise.setSize(enterpriseDtoRegister.getSize());

        // Guardar la empresa y generar el token JWT
        baseAppUserRepository.save(enterprise);
        return jwtService.generateToken(new AppUserDetails(enterprise));
    }

    // Método para registrar un Freelancer
    public String registerFreelancer(FreelancerDtoRegister freelancerDtoRegister) throws Exception {
        if (baseAppUserRepository.findByEmail(freelancerDtoRegister.getEmail()).isPresent()) {
            throw new Exception("El correo ya está en uso");
        }

        // Crear un nuevo Freelancer a partir del DTO
        Freelancer freelancer = new Freelancer();
        freelancer.setEmail(freelancerDtoRegister.getEmail());
        freelancer.setPassword(passwordEncoder.encode(freelancerDtoRegister.getPassword()));
        freelancer.setFirstName(freelancerDtoRegister.getFirstName());
        freelancer.setLastName(freelancerDtoRegister.getLastName());
        freelancer.setAddress(freelancerDtoRegister.getAddress());

        // Guardar el freelancer y generar el token JWT
        baseAppUserRepository.save(freelancer);
        return jwtService.generateToken(new AppUserDetails(freelancer));
    }

    // Puedes añadir otros métodos aquí, como el registro de usuarios
}

