package kdt.minone.domain.department.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeDto {

    private final Long employeeId;
    private final String email;
    private final String name;
    private final String phone;
    private final String role;
}
