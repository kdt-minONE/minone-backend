package kdt.minone.domain.department.service;

import kdt.minone.domain.department.dto.DepartmentEmployeeListResDto;
import kdt.minone.domain.department.dto.DepartmentResDto;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional(readOnly = true)
    public List<DepartmentResDto> findAll() {
        List<Department> departments = departmentRepository.findAll();

        return departments.stream()
                .map(DepartmentResDto::new)
                .toList();
    }

    @Transactional(readOnly = true)
    public DepartmentEmployeeListResDto findEmployeesById(Long departmentId) {
        Department department = departmentRepository.findByIdOrElseThrow(departmentId);
        List<Employee> employees = employeeRepository.findAllByDepartmentId(departmentId);

        return new DepartmentEmployeeListResDto(department, employees);
    }
}
