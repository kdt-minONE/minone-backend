package kdt.minone.domain.complaint.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.complaint.dto.ComplaintMemoCreateReqDto;
import kdt.minone.domain.complaint.dto.ComplaintMemoDetailResDto;
import kdt.minone.domain.complaint.dto.ComplaintMemoUpdateReqDto;
import kdt.minone.domain.complaint.service.ComplaintMemoService;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.common.dto.BaseListResDto;
import kdt.minone.global.common.dto.BaseResDto;
import kdt.minone.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<BaseListResDto<ComplaintMemoDetailResDto>> findAll(
            @PathVariable Long complaintId
    ) {

        List<ComplaintMemoDetailResDto> results = complaintMemoService.findAll(complaintId);

        return new ResponseEntity<>(new BaseListResDto<>(
                "메모 전체 조회 성공",
                results
        ), HttpStatus.OK);
    }

    @PatchMapping("/{memoId}")
    public ResponseEntity<BaseResDto<ComplaintMemoDetailResDto>> updateMemoById(
            @PathVariable Long complaintId,
            @PathVariable Long memoId,
            @RequestBody ComplaintMemoUpdateReqDto dto
    ) {

        ComplaintMemoDetailResDto result = complaintMemoService.updateMemoById(
                complaintId,
                memoId,
                dto.getTitle(),
                dto.getContent()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "메모 수정 성공",
                result
        ), HttpStatus.OK);
    }

    @DeleteMapping("/{memoId}")
    public ResponseEntity<Void> deleteMemoById(
            @PathVariable Long complaintId,
            @PathVariable Long memoId
    ) {

        complaintMemoService.deleteMemoById(complaintId, memoId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
