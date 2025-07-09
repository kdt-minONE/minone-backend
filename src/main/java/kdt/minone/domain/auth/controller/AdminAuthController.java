package kdt.minone.domain.auth.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.auth.dto.AdminSignupReqDto;
import kdt.minone.domain.auth.dto.AdminSignupResDto;
import kdt.minone.domain.auth.service.AdminAuthService;
import kdt.minone.global.common.dto.BaseResDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/admin/auth")
public class AdminAuthController {

    private final AdminAuthService adminAuthService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResDto<AdminSignupResDto>> signup(
            @Valid @RequestBody AdminSignupReqDto dto
    ) {

        AdminSignupResDto result = adminAuthService.signup(
                dto.getEmail(),
                dto.getPassword(),
                dto.getName(),
                dto.getPhone(),
                dto.getDepartmentId()
        );

        return new ResponseEntity<>(new BaseResDto<>("success", result), HttpStatus.CREATED);
    }
}
