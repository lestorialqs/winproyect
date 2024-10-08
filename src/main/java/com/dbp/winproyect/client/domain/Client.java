package com.dbp.winproyect.client.domain;


import com.dbp.winproyect.user.domain.User;
import jakarta.persistence.Entity;

@Entity
public class Client extends User {
    private Integer dni;
    private Boolean showAds;

}
