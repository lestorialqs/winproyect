package com.dbp.winproyect.chat.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MessageDto {
    private Long chatRoomId;  // ID de la sala de chat
    private Long senderId;    // ID del usuario que envía el mensaje (Cliente o Enterprise)
    private String content;   // Contenido del mensaje
    private LocalDateTime sentAt;  // Hora en la que fue enviado el mensaje
}