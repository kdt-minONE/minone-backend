package kdt.minone.domain.complaint.dto;

import kdt.minone.domain.complaint.entity.ComplaintMemo;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ComplaintMemoDetailResDto implements BaseDtoDataType {

    private final Long id;
    private final Long complaintId;
    private final Long employeeId;
    private final String title;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ComplaintMemoDetailResDto(ComplaintMemo complaintMemo) {
        this.id = complaintMemo.getId();
        this.complaintId = complaintMemo.getComplaint().getId();
        this.employeeId = complaintMemo.getEmployee().getId();
        this.title = complaintMemo.getTitle();
        this.content = complaintMemo.getContent();
        this.createdAt = complaintMemo.getCreatedAt();
        this.updatedAt = complaintMemo.getUpdatedAt();
    }
}
