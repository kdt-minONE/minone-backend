package kdt.minone.domain.user.service;

import jakarta.transaction.Transactional;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;

    @Transactional
    public void deleteCitizenById(Long id) {
        Citizen citizen = citizenRepository.findByIdOrElseThrow(id);
        citizenRepository.delete(citizen);
    }
}
