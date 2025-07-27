package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "complaint_result")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE complaint_result SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ComplaintResult extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    private boolean isDeleted;

    @OneToMany(mappedBy = "complaintResult", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintFile> complaintFiles = new ArrayList<>();

    public ComplaintResult(Complaint complaint, String content) {
        this.complaint = complaint;
        this.content = content;
    }

    public void updateResult(String content) {
        this.content = content;
    }
}
