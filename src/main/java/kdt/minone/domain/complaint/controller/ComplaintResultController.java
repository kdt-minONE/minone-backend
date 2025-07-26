package kdt.minone.domain.complaint.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.complaint.dto.ComplaintResultCreateReqDto;
import kdt.minone.domain.complaint.dto.ComplaintResultDetailResDto;
import kdt.minone.domain.complaint.service.ComplaintResultService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/complaints/{complaintId}/results")
public class ComplaintResultController {

    private final ComplaintResultService complaintResultService;

    @PostMapping
    public ResponseEntity<BaseResDto<ComplaintResultDetailResDto>> createResult(
            @PathVariable Long complaintId,
            @Valid @RequestBody ComplaintResultCreateReqDto dto
    ) {

        ComplaintResultDetailResDto result = complaintResultService.createResult(
                complaintId,
                dto.getContent()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "처리 결과 작성 성공",
                result
        ), HttpStatus.CREATED);
    }

    @DeleteMapping("/{resultId}")
    public ResponseEntity<Void> deleteResultById(
            @PathVariable Long complaintId,
            @PathVariable Long resultId
    ) {

        complaintResultService.deleteResultById(complaintId, resultId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
