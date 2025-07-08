package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import kdt.minone.domain.chat.entity.ChatHistory;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "complaint_file")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE complaint_file SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ComplaintFile extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_history_id")
    private ChatHistory chatHistory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id")
    private Complaint complaint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "result_id")
    private ComplaintResult complaintResult;

    @Column(length = 2048, nullable = false)
    private String url;

    private boolean isDeleted;

    public ComplaintFile(ChatHistory chatHistory, Complaint complaint, ComplaintResult complaintResult, String url) {
        this.chatHistory = chatHistory;
        this.complaint = complaint;
        this.complaintResult = complaintResult;
        this.url = url;
    }
}
