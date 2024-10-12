package com.dbp.winproyect.appuser.domain;


import com.dbp.winproyect.appuser.infrastructure.BaseAppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {

    @Autowired
    private BaseAppUserRepository baseAppUserRepository; // Repositorio para manejar la persistencia de usuarios

    @Autowired
    private PasswordEncoder passwordEncoder; // Para codificar la contraseña

    public String registerUser(AppserDtoRegister userDto) throws Exception {
        // Verificar si el usuario ya existe
        if (BaseUserRepository.existsByUsername(userDto.getUsername())) {
            throw new Exception("El nombre de usuario ya está registrado");
        }

        // Crear un nuevo usuario y codificar la contraseña
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(passwordEncoder.encode(userDto.getPassword())); // Codificar la contraseña
        user.setEmail(userDto.getEmail());

        // Guardar el nuevo usuario en la base de datos
        BaseUserRepository.save(user);

        return "Usuario registrado exitosamente";
    }
}
