package kdt.minone.domain.user.service;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import kdt.minone.domain.user.dto.EmployeeListResDto;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminResDto;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public List<EmployeeListResDto> findAllWithDepartment(int page, int size) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return employeeRepository.findAllWithDepartment(pageable).stream().toList();
    }

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

    @Transactional
    public void updateEmployeePassword(Long id, String oldPassword, String newPassword) {

        Employee employee = employeeRepository.findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(oldPassword, employee.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "wrong password");
        }

        employee.updatePassword(passwordEncoder.encode(newPassword));
    }
}
