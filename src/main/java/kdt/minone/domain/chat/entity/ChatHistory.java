package kdt.minone.domain.chat.entity;

import jakarta.persistence.*;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "chat_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    private boolean isAi = true;

    public ChatHistory(String content) {
        this.content = content;
    }
}
