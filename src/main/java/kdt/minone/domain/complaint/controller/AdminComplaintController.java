package kdt.minone.domain.complaint.controller;

import kdt.minone.domain.complaint.dto.EmployeeComplaintListResDto;
import kdt.minone.domain.complaint.service.AdminComplaintService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/complaints")
public class AdminComplaintController {

    private final AdminComplaintService adminComplaintService;

    @GetMapping
    public ResponseEntity<BaseResDto<EmployeeComplaintListResDto>> searchComplaintsByAdmin(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long employeeId,
            @RequestParam(required = false) Long departmentId,
            @RequestParam(required = false) String status
    ) {

        EmployeeComplaintListResDto result = adminComplaintService.searchComplaintsByAdmin(
                page,
                size,
                employeeId,
                departmentId,
                status
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "민원 전체 조회 성공",
                result
        ), HttpStatus.OK);
    }
}
