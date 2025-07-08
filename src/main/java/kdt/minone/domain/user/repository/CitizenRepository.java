package kdt.minone.domain.user.repository;

import kdt.minone.domain.user.entity.Citizen;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CitizenRepository extends JpaRepository<Citizen, Long> {
}
