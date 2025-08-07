package kdt.minone.domain.chat.dto;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MessageResDto {

    private final Long messageId;
    private final boolean isAi;
    private final String content;
    private final LocalDateTime createdAt;

    @QueryProjection
    public MessageResDto(Long messageId, boolean isAi, String content, LocalDateTime createdAt) {
        this.messageId = messageId;
        this.isAi = isAi;
        this.content = content;
        this.createdAt = createdAt;
    }
}
