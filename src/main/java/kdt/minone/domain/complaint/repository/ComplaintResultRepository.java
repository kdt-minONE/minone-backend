package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintResultRepository extends JpaRepository<ComplaintResult, Long> {
}
