package kdt.minone.domain.user.service;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminResDto;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public void deleteEmployeeById(Long employeeId) {
        Employee employee = employeeRepository.findByIdOrElseThrow(employeeId);
        employeeRepository.delete(employee);
    }

    @Transactional
    public EmployeeUpdateByAdminResDto updateEmployeeById(Long employeeId, Long departmentId, String role) {
        Employee employee = employeeRepository.findByIdOrElseThrow(employeeId);
        changeRoleIfPresent(role, employee);
        changeDepartmentIfPresent(departmentId, employee);

        return new EmployeeUpdateByAdminResDto(employee, employee.getDepartment());
    }

    private void changeDepartmentIfPresent(Long departmentId, Employee employee) {
        if (departmentId != null) {
            Department department = departmentRepository.findByIdOrElseThrow(departmentId);
            employee.changeDepartment(department);
        }
    }

    private void changeRoleIfPresent(String role, Employee employee) {
        if (role != null) {
            employee.changeRole(role);
        }
    }
}
