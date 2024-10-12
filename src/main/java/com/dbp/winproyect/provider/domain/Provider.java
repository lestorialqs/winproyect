package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Provider extends AppUser {
    private Long ruc;
    private Boolean estate;
    private Float rating;







}
