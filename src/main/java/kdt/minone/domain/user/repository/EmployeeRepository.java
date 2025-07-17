package kdt.minone.domain.user.repository;

import kdt.minone.domain.user.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, CustomEmployeeRepository {

    default Employee findByIdOrElseThrow(Long employeeId) {
        return findById(employeeId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }

    Optional<Employee> findByEmail(String email);

    List<Employee> findAllByDepartmentId(Long departmentId);
}
