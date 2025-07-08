package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintMemo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintMemoRepository extends JpaRepository<ComplaintMemo, Long> {
}
