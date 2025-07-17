package kdt.minone.domain.user.entity;

import jakarta.persistence.*;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintMemo;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.global.common.entity.BaseEntity;
import kdt.minone.global.enums.EmployeeRole;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

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

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintMemo> complaintMemos = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        assignDefaultRole();
    }

    public Employee(Department department, String email, String name, String password, String phone) {
        this.department = department;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
    }

    public void changeDepartment(Department department) {
        this.department = department;
    }

    public void changeRole(String role) {
        this.role = EmployeeRole.of(role.toUpperCase());
    }

    private void assignDefaultRole() {
        if (this.role == null) {
            this.role = EmployeeRole.EMPLOYEE;
        }
    }

    public void updatePassword(String encoded) {
        this.password = encoded;
    }
}
