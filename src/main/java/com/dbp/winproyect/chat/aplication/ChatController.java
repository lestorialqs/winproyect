package com.dbp.winproyect.chat.aplication;

import com.dbp.winproyect.chat.domain.Message;
import com.dbp.winproyect.chat.dto.MessageDto;
import com.dbp.winproyect.chat.domain.ChatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody MessageDto messageDto) {
        // Lógica para enviar el mensaje
        Message message = chatService.sendMessage(messageDto.getChatRoomId(), messageDto.getSenderId(), messageDto.getContent());
        return ResponseEntity.ok(message);
    }

    @GetMapping("/room/{chatRoomId}")
    public ResponseEntity<List<Message>> getChatRoomMessages(@PathVariable Long chatRoomId) {
        // Lógica para obtener los mensajes de la sala de chat
        List<Message> messages = chatService.getChatRoomMessages(chatRoomId);
        return ResponseEntity.ok(messages);
    }
}
