package kdt.minone.domain.user.dto;

import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeDetailResDto implements BaseDtoDataType {

    private final Long id;
    private final Long departmentId;
    private final String departmentName;
    private final String email;
    private final String name;
    private final String phone;
    private final String role;

    public EmployeeDetailResDto(Employee employee) {
        this.id = employee.getId();
        this.departmentId = employee.getDepartment().getId();
        this.departmentName = employee.getDepartment().getName();
        this.email = employee.getEmail();
        this.name = employee.getName();
        this.phone = employee.getPhone();
        this.role = employee.getRole().name();
    }
}
