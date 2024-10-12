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
    private String email;
    private String password;
    private String phoneNumber;
    private Date registrationDate;
    private String address;
    private Rol rol;

    @OneToOne
    @JoinColumn(name = "location_id")
    private Location location;




}
