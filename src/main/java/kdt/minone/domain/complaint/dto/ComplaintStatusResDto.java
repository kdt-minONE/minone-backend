package kdt.minone.domain.complaint.dto;

import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class ComplaintStatusResDto implements BaseDtoDataType {

    private final Long id;
    private final String status;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public ComplaintStatusResDto(Complaint complaint) {
        this.id = complaint.getId();
        this.status = complaint.getStatus().name();
        this.createdAt = complaint.getCreatedAt();
        this.updatedAt = complaint.getUpdatedAt();
    }
}
