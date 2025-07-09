package kdt.minone.domain.department.dto;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class DepartmentEmployeeListResDto implements BaseDtoDataType {

    private final Long departmentId;
    private final String departmentName;
    private final List<EmployeeDetailResDto> employees;

    public static DepartmentEmployeeListResDto toDto(Department department) {
        List<EmployeeDetailResDto> employees = department.getEmployees().stream()
                .map(employee -> new EmployeeDetailResDto(
                                employee.getId(),
                                employee.getEmail(),
                                employee.getName(),
                                employee.getPhone(),
                                employee.getRole().name()
                        )
                )
                .toList();

        return new DepartmentEmployeeListResDto(
                department.getId(),
                department.getName(),
                employees
        );
    }
}
