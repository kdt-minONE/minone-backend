package kdt.minone.domain.complaint.controller;

import kdt.minone.domain.complaint.dto.CitizenComplaintListResDto;
import kdt.minone.domain.complaint.service.UserComplaintService;
import kdt.minone.global.common.dto.BaseListResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users/{userId}/complaints")
public class UserComplaintController {

    private final UserComplaintService userComplaintService;

    @GetMapping
    public ResponseEntity<BaseListResDto<CitizenComplaintListResDto>> searchComplaintsByUser(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String complaintNo,
            @RequestParam(required = false) String content,
            @RequestParam(required = false) String status
    ) {

        List<CitizenComplaintListResDto> results = userComplaintService.searchComplaintsByUser(
                userId,
                page,
                size,
                complaintNo,
                content,
                status
        );

        return new ResponseEntity<>(new BaseListResDto<>(
                "시민 민원 전체 조회 성공",
                results
        ), HttpStatus.OK);
    }
}
