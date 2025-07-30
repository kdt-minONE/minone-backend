package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.dto.CitizenComplaintListResDto;
import kdt.minone.domain.complaint.dto.EmployeeComplaintListResDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomComplaintRepository {

    Page<CitizenComplaintListResDto> searchComplaintsByUser(Pageable pageable, Long userId, String complaintNo, String content, String status);

    EmployeeComplaintListResDto searchComplaintsByAdmin(Pageable pageable, Long employeeId, Long departmentId, String status);
}
