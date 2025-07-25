package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.ComplaintResultDetailResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintResult;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.complaint.repository.ComplaintResultRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComplaintResultService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintResultRepository complaintResultRepository;

    @Transactional
    public ComplaintResultDetailResDto createResult(Long complaintId, String content) {
        Complaint complaint = complaintRepository.findByIdOrElseThrow(complaintId);

        ComplaintResult result = new ComplaintResult(complaint, content);
        complaintResultRepository.save(result);

        return new ComplaintResultDetailResDto(result);
    }
}
