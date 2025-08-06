package kdt.minone.domain.complaint.controller;

import kdt.minone.domain.complaint.dto.ComplaintStatusReqDto;
import kdt.minone.domain.complaint.dto.ComplaintStatusResDto;
import kdt.minone.domain.complaint.dto.EmployeeComplaintDetailResDto;
import kdt.minone.domain.complaint.dto.EmployeeComplaintListResDto;
import kdt.minone.domain.complaint.service.AdminComplaintService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/{complaintId}")
    public ResponseEntity<BaseResDto<EmployeeComplaintDetailResDto>> findComplaintById(
            @PathVariable Long complaintId
    ) {

        EmployeeComplaintDetailResDto result = adminComplaintService.findComplaintById(complaintId);

        return new ResponseEntity<>(new BaseResDto<>(
                "민원 단건 조회 성공",
                result
        ), HttpStatus.OK);
    }

    @PatchMapping("/{complaintId}")
    public ResponseEntity<BaseResDto<ComplaintStatusResDto>> changeStatusById(
            @PathVariable Long complaintId,
            @RequestBody ComplaintStatusReqDto dto
    ) {

        ComplaintStatusResDto result = adminComplaintService.changeStatusById(
                complaintId,
                dto.getStatus()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "민원 처리 상태 변경 성공",
                result
        ), HttpStatus.OK);
    }
}
