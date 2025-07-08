package kdt.minone.domain.user.entity;

import jakarta.persistence.*;
import kdt.minone.global.common.entity.BaseEntity;
import kdt.minone.global.enums.EmployeeRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "employee")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@SQLDelete(sql = "UPDATE employee SET is_deleted = true WHERE id = ?")
@SQLRestriction("is_deleted = false")
public class Employee extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(length = 200, nullable = false)
    private String password;

    @Column(length = 20, nullable = false)
    private String phone;

    @Column(length = 30, nullable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private EmployeeRole role;

    private boolean isDeleted;

    @PrePersist
    public void prePersist() {
        assignDefaultRole();
    }

    public Employee(String email, String name, String password, String phone) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    private void assignDefaultRole() {
        if (this.role == null) {
            this.role = EmployeeRole.EMPLOYEE;
        }
    }
}
