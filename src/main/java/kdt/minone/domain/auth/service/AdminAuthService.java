package kdt.minone.domain.auth.service;

import jakarta.transaction.Transactional;
import kdt.minone.domain.auth.dto.AdminLoginResDto;
import kdt.minone.domain.auth.dto.AdminSignupResDto;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.CitizenRepository;
import kdt.minone.domain.user.repository.EmployeeRepository;
import kdt.minone.global.config.auth.UserDetailsImpl;
import kdt.minone.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
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
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

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

    public AdminLoginResDto login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Employee employee = userDetails.getEmployee();

        String accessToken = jwtProvider.createEmployeeAccessToken(
                employee.getId(),
                employee.getEmail(),
                employee.getRole(),
                employee.getDepartment().getId()
        );

        String refreshToken = jwtProvider.createRefreshToken(employee.getRole().toString(), employee.getId());

        return new AdminLoginResDto(employee, accessToken, refreshToken);

    }

    public AdminLoginResDto refresh(String refreshToken) {

        // TODO: validateToken -> validateRefreshToken (add valid refreshToken using redis)
        if (!jwtProvider.validateToken(refreshToken)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "invalid token");
        }

        String email = jwtProvider.getUserEmail(refreshToken);

        Employee employee = employeeRepository.findByEmail(email).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "not exists")
        );
        
        String newAccessToken = jwtProvider.createEmployeeAccessToken(
                employee.getId(),
                employee.getEmail(),
                employee.getRole(),
                employee.getDepartment().getId()
        );
        String newRefreshToken = jwtProvider.createRefreshToken(employee.getRole().toString(), employee.getId());

        return new AdminLoginResDto(employee, newAccessToken, newRefreshToken);
    }
}
