package kdt.minone.domain.chat.repository;

import kdt.minone.domain.chat.entity.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long>, CustomChatHistoryRepository {
}
