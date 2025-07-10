package kdt.minone.domain.auth.service;

import jakarta.transaction.Transactional;
import kdt.minone.domain.auth.dto.CitizenLoginResDto;
import kdt.minone.domain.auth.dto.CitizenSignupResDto;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.CitizenRepository;
import kdt.minone.domain.user.repository.EmployeeRepository;
import kdt.minone.global.config.auth.UserDetailsImpl;
import kdt.minone.global.util.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CitizenAuthService {

    private final CitizenRepository citizenRepository;
    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    @Transactional
    public CitizenSignupResDto signup(String email, String password, String name, String phone) {

        Optional<Employee> employee = employeeRepository.findByEmail(email);
        if (employee.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already exists");
        }

        Optional<Citizen> existCitizen = citizenRepository.findByEmail(email);
        if (existCitizen.isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "already exists");
        }

        String encodedPassword = passwordEncoder.encode(password);

        Citizen citizen = new Citizen(email, name, encodedPassword, phone);
        citizenRepository.save(citizen);

        return new CitizenSignupResDto(citizen);
    }

    public CitizenLoginResDto login(String email, String password) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, password)
        );

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        Citizen citizen = userDetails.getCitizen();

        String accessToken = jwtProvider.createCitizenAccessToken(
                citizen.getId(),
                citizen.getEmail()
        );

        String refreshToken = jwtProvider.createRefreshToken("CITIZEN", citizen.getId());

        return new CitizenLoginResDto(citizen.getId(), accessToken, refreshToken, citizen.getEmail(), citizen.getName(), "CITIZEN");
    }
}
