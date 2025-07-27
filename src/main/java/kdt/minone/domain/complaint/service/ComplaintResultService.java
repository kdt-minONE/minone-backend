package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.ComplaintResultDetailResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintResult;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.complaint.repository.ComplaintResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ComplaintResultService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintResultRepository complaintResultRepository;

    @Transactional
    public ComplaintResultDetailResDto createResult(Long complaintId, String content) {
        boolean hasResult = complaintResultRepository.existsByComplaintId(complaintId);
        if (hasResult) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "이미 처리 결과가 존재합니다.");
        }

        Complaint complaint = complaintRepository.findByIdOrElseThrow(complaintId);

        ComplaintResult result = new ComplaintResult(complaint, content);
        complaintResultRepository.save(result);

        return new ComplaintResultDetailResDto(result);
    }

    @Transactional
    public void deleteResultById(Long complaintId, Long resultId) {
        ComplaintResult result = complaintResultRepository.findByIdAndComplaintId(resultId, complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        complaintResultRepository.delete(result);
    }

    @Transactional
    public ComplaintResultDetailResDto updateResultById(Long complaintId, Long resultId, String content) {
        ComplaintResult result = complaintResultRepository.findByIdAndComplaintId(resultId, complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        result.updateResult(content);

        return new ComplaintResultDetailResDto(result);
    }
}
