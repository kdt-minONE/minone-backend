package kdt.minone.domain.department.repository;

import kdt.minone.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
