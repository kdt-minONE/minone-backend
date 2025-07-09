package kdt.minone.domain.department.service;

import kdt.minone.domain.department.dto.DepartmentEmployeeResDto;
import kdt.minone.domain.department.entity.Department;
import kdt.minone.domain.department.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Transactional(readOnly = true)
    public DepartmentEmployeeResDto findEmployeeByDepartment(Long departmentId) {
        Department department = departmentRepository.findByIdOrElseThrow(departmentId);

        return DepartmentEmployeeResDto.toDto(department);
    }
}
