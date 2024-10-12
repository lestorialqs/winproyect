package com.dbp.winproyect.chat.domain;

import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.client.domain.Client;
import com.dbp.winproyect.enterprise.domain.Enterprise;
import com.dbp.winproyect.provider.domain.Provider;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Provider provider;

    // Otros campos como timestamps o estados pueden añadirse.
}


