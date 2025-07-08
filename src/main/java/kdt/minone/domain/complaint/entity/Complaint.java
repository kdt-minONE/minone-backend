package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import kdt.minone.global.common.entity.BaseEntity;
import kdt.minone.global.enums.ComplaintStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.*;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "complaint")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE complaint SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Complaint extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200, unique = true, nullable = false)
    @UuidGenerator(style = UuidGenerator.Style.RANDOM)
    private String complaintNo;

    @Column(length = 100, nullable = false)
    private String address;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(nullable = false, columnDefinition = "text")
    private String content;

    @Column(length = 30, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private ComplaintStatus status;

    private int priorityScore;

    private boolean isDeleted;
}
