package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.CitizenComplaintDetailResDto;
import kdt.minone.domain.complaint.dto.CitizenComplaintListResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintResult;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.complaint.repository.ComplaintResultRepository;
import kdt.minone.domain.user.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserComplaintService {

    private final ComplaintRepository complaintRepository;
    private final CitizenRepository citizenRepository;
    private final ComplaintResultRepository complaintResultRepository;

    @Transactional(readOnly = true)
    public List<CitizenComplaintListResDto> searchComplaintsByUser(Long userId, int page, int size, String complaintNo, String content, String status) {
        citizenRepository.findByIdOrElseThrow(userId);
        Pageable pageable = PageRequest.of(page - 1, size);

        return complaintRepository.searchComplaintsByUser(pageable, userId, complaintNo, content, status).stream().toList();
    }

    @Transactional(readOnly = true)
    public CitizenComplaintDetailResDto findComplaintById(Long userId, Long complaintId) {
        Complaint complaint = complaintRepository.findByIdAndCitizenId(complaintId, userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        ComplaintResult result = complaintResultRepository.findByComplaintId(complaintId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));

        return new CitizenComplaintDetailResDto(complaint, result);
    }
}
