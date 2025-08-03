package kdt.minone.domain.complaint.dto;

import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class EmployeeComplaintListResDto implements BaseDtoDataType {

    private final Long total;
    private final List<EmployeeComplaintDetailResDto> complaints;
}
