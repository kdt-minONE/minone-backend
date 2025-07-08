package kdt.minone.domain.complaint.repository;

import kdt.minone.domain.complaint.entity.ComplaintFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplaintFileRepository extends JpaRepository<ComplaintFile, Long> {
}
