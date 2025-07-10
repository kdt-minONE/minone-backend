package kdt.minone.domain.department.dto;

import kdt.minone.domain.user.entity.Employee;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class EmployeeDetailResDto {

    private final Long employeeId;
    private final String email;
    private final String name;
    private final String phone;
    private final String role;

    public static EmployeeDetailResDto from(Employee employee) {
        return new EmployeeDetailResDto(
                employee.getId(),
                employee.getEmail(),
                employee.getName(),
                employee.getPhone(),
                employee.getRole().name()
        );
    }
}
