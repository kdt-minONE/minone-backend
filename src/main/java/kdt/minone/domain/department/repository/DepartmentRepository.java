package kdt.minone.domain.department.repository;

import kdt.minone.domain.department.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    default Department findByIdOrElseThrow(Long departmentId) {
        return findById(departmentId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }
}
