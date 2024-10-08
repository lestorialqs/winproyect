package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.user.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity

public class Provider extends User {

    private Boolean estate;
    private Float rating;






}
