package com.dbp.winproyect.chat.domain;

import com.dbp.winproyect.appuser.domain.AppUser;
import com.dbp.winproyect.appuser.infrastructure.BaseAppUserRepository;
import com.dbp.winproyect.chat.dto.MessageDto;
import com.dbp.winproyect.chat.repository.ChatRoomRepository;
import com.dbp.winproyect.chat.repository.MessageRepository;
import com.dbp.winproyect.exceptions.ResourceNotFoundException;
import com.pusher.rest.Pusher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ChatService {

    private final ChatRoomRepository chatRoomRepository;
    private final MessageRepository messageRepository;
    private final BaseAppUserRepository appUserRepository;  // Repositorio para obtener el usuario
    private final Pusher pusher;

    @Autowired
    public ChatService(ChatRoomRepository chatRoomRepository, MessageRepository messageRepository, BaseAppUserRepository appUserRepository, Pusher pusher) {
        this.chatRoomRepository = chatRoomRepository;
        this.messageRepository = messageRepository;
        this.appUserRepository = appUserRepository;
        this.pusher = pusher;
    }

    public Message sendMessage(Long chatRoomId, Long senderId, String content) {
        // Obtén la sala de chat
        Chat chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("ChatRoom no encontrado"));

        // Obtén el usuario (Cliente o Enterprise) por su ID
        AppUser sender = appUserRepository.findById(senderId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario no encontrado"));

        // Crea y guarda el mensaje
        Message message = new Message();
        message.setContent(content);
        message.setChatRoom(chatRoom);
        message.setSender(sender);
        message.setSentAt(LocalDateTime.now());

        messageRepository.save(message);

        // Publica el mensaje en Pusher
        MessageDto messageDto = new MessageDto();
        messageDto.setChatRoomId(chatRoomId);
        messageDto.setSenderId(senderId);
        messageDto.setContent(content);

        pusher.trigger("chat-channel", "new-message", messageDto);

        return message;
    }

    public List<Message> getChatRoomMessages(Long chatRoomId) {
        // Obtén la sala de chat
        Chat chatRoom = chatRoomRepository.findById(chatRoomId)
                .orElseThrow(() -> new ResourceNotFoundException("ChatRoom no encontrado"));

        // Retorna la lista de mensajes de esa sala
        return messageRepository.findByChatRoom(chatRoom);
    }
}
