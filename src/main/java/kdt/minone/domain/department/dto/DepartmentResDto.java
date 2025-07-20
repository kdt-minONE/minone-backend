package kdt.minone.domain.department.dto;

import kdt.minone.domain.department.entity.Department;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DepartmentResDto implements BaseDtoDataType {

    private final Long id;
    private final String name;

    public DepartmentResDto(Department department) {
        this.id = department.getId();
        this.name = department.getName();
    }
}
