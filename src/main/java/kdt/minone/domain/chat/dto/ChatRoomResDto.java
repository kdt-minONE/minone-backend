package kdt.minone.domain.chat.dto;

import kdt.minone.domain.chat.entity.ChatRoom;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ChatRoomResDto implements BaseDtoDataType {

    private final Long id;
    private final Long citizenId;
    private final String title;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ChatRoomResDto(ChatRoom chatRoom) {
        this.id = chatRoom.getId();
        this.citizenId = chatRoom.getCitizen().getId();
        this.title = chatRoom.getTitle();
        this.createdAt = chatRoom.getCreatedAt();
        this.updatedAt = chatRoom.getUpdatedAt();
    }
}
