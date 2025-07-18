package kdt.minone.domain.user.dto;

import com.querydsl.core.annotations.QueryProjection;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;

@Getter
public class EmployeeListResDto implements BaseDtoDataType {

    private final Long id;
    private final Long departmentId;
    private final String departmentName;
    private final String email;
    private final String name;
    private final String phone;
    private final String role;

    @QueryProjection
    public EmployeeListResDto(Long id, Long departmentId, String departmentName, String email,
                              String name, String phone, String role) {
        this.id = id;
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.email = email;
        this.name = name;
        this.phone = phone;
        this.role = role;
    }
}
