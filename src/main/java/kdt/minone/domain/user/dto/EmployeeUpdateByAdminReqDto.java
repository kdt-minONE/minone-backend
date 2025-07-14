package kdt.minone.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeUpdateByAdminReqDto {

    private final Long departmentId;

    private final String role;
}
