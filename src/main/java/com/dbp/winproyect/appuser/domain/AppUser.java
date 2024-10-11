package com.dbp.winproyect.appuser.domain;

import com.dbp.winproyect.location.domain.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Table(name = "app_user")
@Inheritance(strategy = InheritanceType.JOINED)
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Date registrationDate;
    private String address;


    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;



    // aca hay mas atributos ? :c si pero pa q

    @Data
    public static class UserLoginDto {
        private String email;
        private String password;
    }

    @Data
    public static class UserRequestDto { // Este DTO es para cuando el usuario se registra o actualiza su información. Sólo necesitas los datos básicos.
        private String email;
        private String password;
        private String phoneNumber;
        private String address;
        private String type;  // Tipo de usuario: CLIENT o PROVIDER
    }

    @Data // Este DTO es para devolver la información del usuario después de realizar acciones como obtener el perfil o la información de contacto del usuario.
    public static class UserResponseDto {
        private Long id_user;
        private String email;
        private String phoneNumber;
        private String address;
        private String type;
        private LocalDateTime registration_date;
    }
}
