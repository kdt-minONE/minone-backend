package kdt.minone.domain.complaint.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComplaintStatusReqDto {

    private final String status;
}
