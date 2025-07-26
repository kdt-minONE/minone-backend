package kdt.minone.domain.complaint.dto;

import kdt.minone.domain.complaint.entity.ComplaintResult;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ComplaintResultDetailResDto implements BaseDtoDataType {

    private final Long id;
    private final Long complaintId;
    private final String content;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ComplaintResultDetailResDto(ComplaintResult complaintResult) {
        this.id = complaintResult.getId();
        this.complaintId = complaintResult.getComplaint().getId();
        this.content = complaintResult.getContent();
        this.createdAt = complaintResult.getCreatedAt();
        this.updatedAt = complaintResult.getUpdatedAt();
    }
}
