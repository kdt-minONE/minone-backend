package kdt.minone.domain.complaint.entity;

import jakarta.persistence.*;
import kdt.minone.domain.chat.entity.ChatRoom;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.entity.BaseEntity;
import kdt.minone.global.enums.ComplaintStatus;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;
import org.hibernate.annotations.UuidGenerator;
import org.hibernate.type.SqlTypes;

import java.util.ArrayList;
import java.util.List;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_room_id", nullable = false)
    private ChatRoom chatRoom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "citizen_id", nullable = false)
    private Citizen citizen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;

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

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintResult> complaintResults = new ArrayList<>();

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintHistory> complaintHistories = new ArrayList<>();

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintFile> complaintFiles = new ArrayList<>();

    @OneToMany(mappedBy = "complaint", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplaintMemo> complaintMemos = new ArrayList<>();

    public Complaint(ChatRoom chatRoom, Citizen citizen, Department department, Employee employee,
                     String complaintNo, String address, String title, String content, ComplaintStatus status, int priorityScore) {
        this.chatRoom = chatRoom;
        this.citizen = citizen;
        this.department = department;
        this.employee = employee;
        this.complaintNo = complaintNo;
        this.address = address;
        this.title = title;
        this.content = content;
        this.status = status;
        this.priorityScore = priorityScore;
    }
}
