package kdt.minone.domain.department.entity;

import jakarta.persistence.*;
import kdt.minone.global.common.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Table(name = "department")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE department SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Department extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 50, nullable = false)
    private String name;

    private boolean isDeleted;

    public Department(String name) {
        this.name = name;
    }
}
