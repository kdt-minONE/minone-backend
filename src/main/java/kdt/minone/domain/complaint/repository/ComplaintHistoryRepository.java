package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintHistoryRepository extends JpaRepository<ComplaintHistory, Long> {
}
