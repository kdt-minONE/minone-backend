package kdt.minone.domain.department.controller;

import kdt.minone.domain.department.dto.DepartmentEmployeeResDto;
import kdt.minone.domain.department.service.DepartmentService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping("/{departmentId}/employees")
    public ResponseEntity<BaseResDto<DepartmentEmployeeResDto>> findEmployeeByDepartment(
            @PathVariable Long departmentId
    ) {

        DepartmentEmployeeResDto result = departmentService.findEmployeeByDepartment(departmentId);

        return new ResponseEntity<>(new BaseResDto<>(
                "부서별 담당자 전체 조회 성공",
                result
        ), HttpStatus.OK);
    }
}
