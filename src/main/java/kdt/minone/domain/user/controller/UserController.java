package kdt.minone.domain.user.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.auth.dto.CitizenSignupResDto;
import kdt.minone.domain.user.dto.CitizenUpdateReqDto;
import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.service.AdminService;
import kdt.minone.domain.user.service.CitizenService;
import kdt.minone.global.common.dto.BaseResDto;
import kdt.minone.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final CitizenService citizenService;
    private final AdminService adminService;

    @GetMapping("/me")
    public ResponseEntity<BaseResDto<CitizenSignupResDto>> getUser(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Citizen citizen = userDetails.getCitizen();

        CitizenSignupResDto result = new CitizenSignupResDto(citizen);

        return new ResponseEntity<>(new BaseResDto<>("success get user data", result), HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<Void> patchUserPassword(
            @Valid @RequestBody CitizenUpdateReqDto dto,
            Authentication authentication
    ) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        String role = userDetails.getAuthorities().iterator().next().getAuthority();

        if (role.equals("ROLE_CITIZEN")) {
            Citizen citizen = userDetails.getCitizen();
            citizenService.updateCitizenPassword(citizen.getId(), dto.getOldPassword(), dto.getNewPassword());
        } else {
            Employee employee = userDetails.getEmployee();
            adminService.updateEmployeePassword(employee.getId(), dto.getOldPassword(), dto.getNewPassword());
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

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
