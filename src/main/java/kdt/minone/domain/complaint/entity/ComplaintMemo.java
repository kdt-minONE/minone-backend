package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "complaint_memo")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE complaint_memo SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class ComplaintMemo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "complaint_id", nullable = false)
    private Complaint complaint;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    private Employee employee;

    @Column(length = 50, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    private boolean isDeleted;

    public ComplaintMemo(Complaint complaint, Employee employee, String title, String content) {
        this.complaint = complaint;
        this.employee = employee;
        this.title = title;
        this.content = content;
    }
}
