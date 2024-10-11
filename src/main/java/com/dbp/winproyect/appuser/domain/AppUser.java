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



}
