package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
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

    @Column(length = 2048, nullable = false)
    private String url;

    private boolean isDeleted;
}
