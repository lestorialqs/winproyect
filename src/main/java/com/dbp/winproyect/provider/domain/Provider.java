package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@Entity
//@NoArgsConstructor
//@RequiredArgsConstructor

public class Provider extends AppUser {
    private Long ruc;
    private Boolean estate;
    private Float rating;
    private double comission;
}
