package kdt.minone.domain.auth.dto;

import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class AdminSignupResDto implements BaseDtoDataType {

    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final String role;
    private final Long departmentId;
    private final String departmentName;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public AdminSignupResDto(Employee employee) {
        this.id = employee.getId();
        this.email = employee.getEmail();
        this.name = employee.getName();
        this.phone = employee.getPhone();
        this.role = employee.getRole().toString();
        this.departmentId = employee.getDepartment().getId();
        this.departmentName = employee.getDepartment().getName();
        this.createdAt = employee.getCreatedAt();
        this.updatedAt = employee.getUpdatedAt();
    }
}
