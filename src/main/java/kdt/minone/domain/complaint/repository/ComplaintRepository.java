package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
}
