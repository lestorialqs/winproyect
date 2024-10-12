package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.*;



//@NoArgsConstructor
@Data // Para generar getters y setters
@Entity
//@RequiredArgsConstructor
//@Setter
//@Getter

public class Provider extends AppUser {
    private Long ruc;
    private Boolean estate;
    private Float rating;
    private double comission;
}
