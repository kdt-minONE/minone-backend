package kdt.minone.domain.chat.service;

import kdt.minone.domain.chat.dto.ChatRoomResDto;
import kdt.minone.domain.chat.entity.ChatRoom;
import kdt.minone.domain.chat.repository.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    @Transactional
    public ChatRoomResDto updateChatRoomById(Long userId, Long chatRoomId, String title) {
        ChatRoom chatRoom = chatRoomRepository.findByIdAndCitizenId(chatRoomId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        chatRoom.updateChatRoom(title);

        return new ChatRoomResDto(chatRoom);
    }

    @Transactional
    public void deleteChatRoomById(Long userId, Long chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findByIdAndCitizenId(chatRoomId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        chatRoomRepository.delete(chatRoom);
    }
}
