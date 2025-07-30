package kdt.minone.domain.complaint.service;

import kdt.minone.domain.complaint.dto.EmployeeComplaintDetailResDto;
import kdt.minone.domain.complaint.dto.EmployeeComplaintListResDto;
import kdt.minone.domain.complaint.entity.Complaint;
import kdt.minone.domain.complaint.repository.ComplaintRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AdminComplaintService {

    private final ComplaintRepository complaintRepository;

    @Transactional(readOnly = true)
    public EmployeeComplaintListResDto searchComplaintsByAdmin(int page, int size, Long employeeId, Long departmentId, String status) {
        Pageable pageable = PageRequest.of(page - 1, size);

        return complaintRepository.searchComplaintsByAdmin(pageable, employeeId, departmentId, status);
    }

    @Transactional(readOnly = true)
    public EmployeeComplaintDetailResDto findComplaintById(Long complaintId) {
        Complaint complaint = complaintRepository.findByIdOrElseThrow(complaintId);

        return new EmployeeComplaintDetailResDto(complaint);
    }
}
