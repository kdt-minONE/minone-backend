package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplaintResultRepository extends JpaRepository<ComplaintResult, Long> {

    boolean existsByComplaintId(Long complaintId);

    Optional<ComplaintResult> findByIdAndComplaintId(Long resultId, Long complaintId);
}
