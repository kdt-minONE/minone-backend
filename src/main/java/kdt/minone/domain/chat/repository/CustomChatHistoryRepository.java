package kdt.minone.domain.chat.repository;

import kdt.minone.domain.chat.dto.MessageResDto;

import java.util.List;

public interface CustomChatHistoryRepository {

    List<MessageResDto> findAllWithLimit(Long chatRoomId, Integer limit);
}
