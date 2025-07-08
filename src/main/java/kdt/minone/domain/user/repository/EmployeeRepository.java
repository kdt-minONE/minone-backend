package kdt.minone.domain.user.repository;

import kdt.minone.domain.user.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
