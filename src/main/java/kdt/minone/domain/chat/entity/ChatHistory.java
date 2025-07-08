package kdt.minone.domain.chat.entity;

import jakarta.persistence.*;
import kdt.minone.domain.complaint.entity.ComplaintFile;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "chat_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ChatHistory extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    private boolean isAi = true;

    @OneToMany(mappedBy = "chatHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintFile> complaintFiles = new ArrayList<>();

    public ChatHistory(ChatRoom chatRoom, String content) {
        this.chatRoom = chatRoom;
        this.content = content;
    }
}
