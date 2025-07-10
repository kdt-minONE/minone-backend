package kdt.minone.domain.auth.dto;

import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class AdminLoginResDto implements BaseDtoDataType {

    private final Long id;
    private final String email;
    private final String name;
    private final String role;
    private final Long departmentId;
    private final String departmentName;
    private final String accessToken;
    private final String refreshToken;

    public AdminLoginResDto(Employee employee, String accessToken, String refreshToken) {
        this.id = employee.getId();
        this.email = employee.getEmail();
        this.name = employee.getName();
        this.role = employee.getRole().toString();
        this.departmentId = employee.getDepartment().getId();
        this.departmentName = employee.getDepartment().getName();
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
