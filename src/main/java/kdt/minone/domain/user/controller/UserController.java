package kdt.minone.domain.user.controller;

import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.service.AdminService;
import kdt.minone.domain.user.service.CitizenService;
import kdt.minone.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final CitizenService citizenService;
    private final AdminService adminService;

    @DeleteMapping("/me")
    public ResponseEntity<Void> deleteUser(
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_CITIZEN")) {
            Citizen citizen = userDetails.getCitizen();
            citizenService.deleteCitizenById(citizen.getId());
        } else {
            Employee employee = userDetails.getEmployee();
            adminService.deleteEmployeeById(employee.getId());
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
