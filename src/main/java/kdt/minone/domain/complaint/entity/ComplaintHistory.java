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

    @Column(length = 30)
    private String previousStatus;

    @Column(length = 30, nullable = false)
    private String nextStatus;

    private String comment;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;
}
