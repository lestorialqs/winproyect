package com.dbp.winproyect.client.domain;


import com.dbp.winproyect.appuser.domain.Role;
import com.dbp.winproyect.arrangement.domain.Arrangement;
import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;
@Data
@Entity
public class Client extends AppUser {

    private String firstName;
    private String lastName;
    private Long dni;
    private Boolean showAds;

}
