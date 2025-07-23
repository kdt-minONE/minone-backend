package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.ComplaintMemoDetailResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.entity.ComplaintMemo;
import kdt.minone.domain.complaint.repository.ComplaintMemoRepository;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import kdt.minone.domain.user.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ComplaintMemoService {

    private final ComplaintRepository complaintRepository;
    private final ComplaintMemoRepository complaintMemoRepository;

    @Transactional
    public ComplaintMemoDetailResDto createMemo(Long complaintId, Employee employee, String title, String content) {
        Complaint complaint = complaintRepository.findByIdOrElseThrow(complaintId);

        ComplaintMemo memo = new ComplaintMemo(complaint, employee, title, content);
        complaintMemoRepository.save(memo);

        return new ComplaintMemoDetailResDto(memo);
    }
}
