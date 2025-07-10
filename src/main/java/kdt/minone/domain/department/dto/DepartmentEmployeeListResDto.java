package kdt.minone.domain.department.dto;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.user.entity.Employee;
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

    public DepartmentEmployeeListResDto(Department department, List<Employee> employees) {
        this.departmentId = department.getId();
        this.departmentName = department.getName();
        this.employees = employees.stream()
                .map(EmployeeDetailResDto::from)
                .toList();
    }
}
