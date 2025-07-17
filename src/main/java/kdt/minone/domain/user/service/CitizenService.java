package kdt.minone.domain.user.service;

import jakarta.transaction.Transactional;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.repository.CitizenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class CitizenService {

    private final CitizenRepository citizenRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void deleteCitizenById(Long id) {
        Citizen citizen = citizenRepository.findByIdOrElseThrow(id);
        citizenRepository.delete(citizen);
    }

    @Transactional
    public void updateCitizenPassword(Long id, String oldPassword, String newPassword) {
        Citizen savedCitizen = citizenRepository.findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(oldPassword, savedCitizen.getPassword())) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "wrong password");
        }

        savedCitizen.updatePassword(passwordEncoder.encode(newPassword));
    }
}
