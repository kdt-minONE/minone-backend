package kdt.minone.domain.user.controller;

import kdt.minone.domain.user.dto.EmployeeDetailResDto;
import kdt.minone.domain.user.dto.EmployeeListResDto;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminReqDto;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminResDto;
import kdt.minone.domain.user.service.AdminService;
import kdt.minone.global.common.dto.BaseListResDto;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master/employees")
public class MasterUserController {

    private final AdminService adminService;

    @GetMapping
    public ResponseEntity<BaseListResDto<EmployeeListResDto>> findAllWithDepartment(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size
    ) {

        List<EmployeeListResDto> results = adminService.findAllWithDepartment(page, size);

        return new ResponseEntity<>(new BaseListResDto<>(
                "공무원 전체 조회 완료",
                results
        ), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<BaseResDto<EmployeeDetailResDto>> findEmployeeById(
            @PathVariable Long employeeId
    ) {

        EmployeeDetailResDto result = adminService.findEmployeeById(employeeId);

        return new ResponseEntity<>(new BaseResDto<>(
                "공무원 단건 조회 완료",
                result
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployeeById(
            @PathVariable Long employeeId
    ) {

        adminService.deleteEmployeeById(employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<BaseResDto<EmployeeUpdateByAdminResDto>> updateEmployeeById(
            @PathVariable Long employeeId,
            @RequestBody EmployeeUpdateByAdminReqDto dto
    ) {

        EmployeeUpdateByAdminResDto result = adminService.updateEmployeeById(
                employeeId,
                dto.getDepartmentId(),
                dto.getRole()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "employee update success",
                result
        ), HttpStatus.OK);
    }
}
