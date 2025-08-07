package kdt.minone.domain.chat.repository;

import kdt.minone.domain.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {

    default ChatRoom findByIdOrElseThrow(Long chatRoomId) {
        return findById(chatRoomId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

    Optional<ChatRoom> findByIdAndCitizenId(Long chatRoomId, Long citizenId);

    List<ChatRoom> findAllByCitizenId(Long userId);
}
