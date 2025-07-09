package kdt.minone.domain.auth.service;

import jakarta.transaction.Transactional;
import kdt.minone.domain.auth.dto.AdminSignupResDto;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.CitizenRepository;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminAuthService {

    private final EmployeeRepository employeeRepository;
    private final CitizenRepository citizenRepository;
    private final DepartmentRepository departmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AdminSignupResDto signup(String email, String password, String name, String phone, Long departmentId) {

        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already exists");
        }

        Optional<Citizen> citizen = citizenRepository.findByEmail(email);
        if (citizen.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already exists");
        }

        Department department = departmentRepository.findByIdOrElseThrow(departmentId);
        String encodedPassword = passwordEncoder.encode(password);

        Employee newEmployee = new Employee(department, email, name, encodedPassword, phone);

        employeeRepository.save(newEmployee);

        return new AdminSignupResDto(newEmployee);
    }
}
