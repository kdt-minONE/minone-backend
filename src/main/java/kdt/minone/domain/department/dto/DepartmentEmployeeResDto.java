package kdt.minone.domain.department.dto;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DepartmentEmployeeResDto implements BaseDtoDataType {

    private final Long departmentId;
    private final String departmentName;
    private final List<EmployeeDto> employees;

    public static DepartmentEmployeeResDto toDto(Department department) {
        List<EmployeeDto> employees = department.getEmployees().stream()
                .map(employee -> new EmployeeDto(
                                employee.getId(),
                                employee.getEmail(),
                                employee.getName(),
                                employee.getPhone(),
                                employee.getRole().name()
                        )
                )
                .toList();

        return new DepartmentEmployeeResDto(
                department.getId(),
                department.getName(),
                employees
        );
    }
}
