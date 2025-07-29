package kdt.minone.domain.complaint.dto;

import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintResult;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CitizenComplaintDetailResDto implements BaseDtoDataType {

    private final Long id;
    private final String complaintNo;
    private final Long departmentId;
    private final String departmentName;
    private final Long employeeId;
    private final String employeeName;
    private final String address;
    private final String title;
    private final String content;
    private final String status;
    private final String result;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CitizenComplaintDetailResDto(Complaint complaint, ComplaintResult complaintResult) {
        this.id = complaint.getId();
        this.complaintNo = complaint.getComplaintNo();
        this.departmentId = complaint.getDepartment().getId();
        this.departmentName = complaint.getDepartment().getName();
        this.employeeId = complaint.getEmployee().getId();
        this.employeeName = complaint.getEmployee().getName();
        this.address = complaint.getAddress();
        this.title = complaint.getTitle();
        this.content = complaint.getContent();
        this.status = complaint.getStatus().name();
        this.result = complaintResult.getContent();
        this.createdAt = complaint.getCreatedAt();
        this.updatedAt = complaint.getUpdatedAt();
    }
}
