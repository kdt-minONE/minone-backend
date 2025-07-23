package kdt.minone.domain.complaint.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.complaint.dto.ComplaintMemoCreateReqDto;
import kdt.minone.domain.complaint.dto.ComplaintMemoDetailResDto;
import kdt.minone.domain.complaint.service.ComplaintMemoService;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.dto.BaseResDto;
import kdt.minone.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/complaints/{complaintId}/memos")
public class ComplaintMemoController {

    private final ComplaintMemoService complaintMemoService;

    @PostMapping
    public ResponseEntity<BaseResDto<ComplaintMemoDetailResDto>> createMemo(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintMemoCreateReqDto dto,
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Employee employee = userDetails.getEmployee();

        ComplaintMemoDetailResDto result = complaintMemoService.createMemo(
                complaintId,
                employee,
                dto.getTitle(),
                dto.getContent()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "메모 작성 성공",
                result
        ), HttpStatus.CREATED);
    }
}
