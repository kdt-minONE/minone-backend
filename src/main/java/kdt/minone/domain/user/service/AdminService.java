package kdt.minone.domain.user.service;

import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final EmployeeRepository employeeRepository;

    @Transactional
    public void deleteEmployee(Long employeeId) {
        Employee employee = employeeRepository.findByIdOrElseThrow(employeeId);
        employeeRepository.delete(employee);
    }
}
