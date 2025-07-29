package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>, CustomComplaintRepository {

    default Complaint findByIdOrElseThrow(Long complaintId) {
        return findById(complaintId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "not found"));
    }
}
