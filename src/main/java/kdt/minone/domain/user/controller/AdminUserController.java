package kdt.minone.domain.user.controller;

import kdt.minone.domain.auth.dto.AdminSignupResDto;
import kdt.minone.domain.user.entity.Employee;
import kdt.minone.domain.user.service.AdminService;
import kdt.minone.global.common.dto.BaseResDto;
import kdt.minone.global.config.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/users")
public class AdminUserController {

    private final AdminService adminService;

    @GetMapping("/me")
    public ResponseEntity<BaseResDto<AdminSignupResDto>> getAdmin(Authentication authentication) {

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Employee employee = userDetails.getEmployee();

        AdminSignupResDto result = new AdminSignupResDto(employee);

        return new ResponseEntity<>(new BaseResDto<>("success get user data", result), HttpStatus.OK);
    }
}
