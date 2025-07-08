package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "complaint_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ComplaintHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    @Column(length = 30)
    private String previousStatus;

    @Column(length = 30, nullable = false)
    private String nextStatus;

    private String comment;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    public ComplaintHistory(Complaint complaint, String previousStatus, String nextStatus, String comment) {
        this.complaint = complaint;
        this.previousStatus = previousStatus;
        this.nextStatus = nextStatus;
        this.comment = comment;
    }
}
