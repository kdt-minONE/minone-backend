package kdt.minone.domain.chat.dto;

import kdt.minone.domain.chat.entity.ChatRoom;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class ChatRoomDetailResDto implements BaseDtoDataType {

    private final Long chatRoomId;
    private final String chatRoomTitle;
    private final List<MessageResDto> messages;

    public ChatRoomDetailResDto(ChatRoom chatRoom, List<MessageResDto> messages) {
        this.chatRoomId = chatRoom.getId();
        this.chatRoomTitle = chatRoom.getTitle();
        this.messages = messages;
    }
}
