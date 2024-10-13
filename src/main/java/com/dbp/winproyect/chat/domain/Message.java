package com.dbp.winproyect.chat.domain;

import com.dbp.winproyect.appuser.domain.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Chat chatRoom;

    @ManyToOne
    private AppUser sender;
}