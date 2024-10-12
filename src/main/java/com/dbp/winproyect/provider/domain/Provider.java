package com.dbp.winproyect.provider.domain;


import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity

public class Provider extends AppUser {

    private Boolean estate;
    private Float rating;
    private String ruc;







}
