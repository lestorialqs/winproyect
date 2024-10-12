package com.dbp.winproyect.chat.repository;

import com.dbp.winproyect.chat.domain.Chat;
import com.dbp.winproyect.chat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatRoom(Chat chatRoom);
}
