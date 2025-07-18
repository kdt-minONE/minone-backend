package kdt.minone.domain.user.repository;

import kdt.minone.domain.user.dto.EmployeeListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomEmployeeRepository {

    Page<EmployeeListResDto> findAllWithDepartment(Pageable pageable);
}
