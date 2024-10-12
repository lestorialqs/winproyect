package com.dbp.winproyect.chat.repository;

import com.dbp.winproyect.chat.domain.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRoomRepository extends JpaRepository<Chat, Long> {
}