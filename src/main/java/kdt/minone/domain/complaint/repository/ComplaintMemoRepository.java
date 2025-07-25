package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintMemo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ComplaintMemoRepository extends JpaRepository<ComplaintMemo, Long> {

    Optional<ComplaintMemo> findByIdAndComplaintId(Long memoId, Long complaintId);
}
