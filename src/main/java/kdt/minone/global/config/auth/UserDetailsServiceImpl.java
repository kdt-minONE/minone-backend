package kdt.minone.global.config.auth;

import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.repository.CitizenRepository;
import kdt.minone.domain.user.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final EmployeeRepository employeeRepository;
    private final CitizenRepository citizenRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Employee> employee = employeeRepository.findByEmail(username);
        Optional<Citizen> citizen = citizenRepository.findByEmail(username);

        if (employee.isEmpty() && citizen.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }

        return employee.map(value -> new UserDetailsImpl(value, null)).orElseGet(() -> new UserDetailsImpl(null, citizen.get()));
    }
}
