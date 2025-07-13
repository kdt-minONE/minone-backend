package kdt.minone.domain.user.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminReqDto;
import kdt.minone.domain.user.dto.EmployeeUpdateByAdminResDto;
import kdt.minone.domain.user.service.AdminService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master/employees")
public class AdminController {

    private final AdminService adminService;

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
            @Valid @RequestBody EmployeeUpdateByAdminReqDto dto
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
