package kdt.minone.domain.complaint.dto;

import com.querydsl.core.annotations.QueryProjection;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CitizenComplaintListResDto implements BaseDtoDataType {

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

    @QueryProjection
    public CitizenComplaintListResDto(Long id, String complaintNo, Long departmentId, String departmentName,
                                      Long employeeId, String employeeName, String address, String title, String content,
                                      String status, String result, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.complaintNo = complaintNo;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.address = address;
        this.title = title;
        this.content = content;
        this.status = status;
        this.result = result;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
