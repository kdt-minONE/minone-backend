package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.CitizenComplaintListResDto;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.user.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserComplaintService {

    private final ComplaintRepository complaintRepository;
    private final CitizenRepository citizenRepository;

    @Transactional(readOnly = true)
    public List<CitizenComplaintListResDto> searchComplaintsByUser(Long userId, int page, int size, String complaintNo, String content, String status) {
        citizenRepository.findByIdOrElseThrow(userId);
        Pageable pageable = PageRequest.of(page - 1, size);

        return complaintRepository.searchComplaintsByUser(pageable, userId, complaintNo, content, status).stream().toList();
    }
}
