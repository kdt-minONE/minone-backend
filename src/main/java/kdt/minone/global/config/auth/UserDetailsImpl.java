package kdt.minone.global.config.auth;

import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.global.enums.EmployeeRole;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {

    private final Employee employee;
    private final Citizen citizen;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        if (employee != null && citizen == null) {
            EmployeeRole role = employee.getRole();

            return List.of(
                    new SimpleGrantedAuthority("ROLE_" + role)
            );
        } else if (citizen != null && employee == null) {

            return List.of(
                    new SimpleGrantedAuthority("ROLE_" + "CITIZEN")
            );
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public String getPassword() {
        if (employee != null && citizen == null) {

            return employee.getPassword();
        } else if (citizen != null && employee == null) {

            return citizen.getPassword();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }

    @Override
    public String getUsername() {
        if (employee != null && citizen == null) {

            return employee.getEmail();
        } else if (citizen != null && employee == null) {

            return citizen.getEmail();
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }
}
