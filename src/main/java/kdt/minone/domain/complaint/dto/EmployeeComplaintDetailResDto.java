package kdt.minone.domain.complaint.dto;

import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class EmployeeComplaintDetailResDto implements BaseDtoDataType {

    private final Long id;
    private final String complaintNo;
    private final Long citizenId;
    private final String citizenName;
    private final String citizenPhone;
    private final Long departmentId;
    private final String departmentName;
    private final String address;
    private final String title;
    private final String content;
    private final int priorityScore;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public EmployeeComplaintDetailResDto(Complaint complaint) {
        this.id = complaint.getId();
        this.complaintNo = complaint.getComplaintNo();
        this.citizenId = complaint.getCitizen().getId();
        this.citizenName = complaint.getCitizen().getName();
        this.citizenPhone = complaint.getCitizen().getPhone();
        this.departmentId = complaint.getDepartment().getId();
        this.departmentName = complaint.getDepartment().getName();
        this.address = complaint.getAddress();
        this.title = complaint.getTitle();
        this.content = complaint.getContent();
        this.priorityScore = complaint.getPriorityScore();
        this.status = complaint.getStatus().name();
        this.createdAt = complaint.getCreatedAt();
        this.updatedAt = complaint.getUpdatedAt();
    }
}
