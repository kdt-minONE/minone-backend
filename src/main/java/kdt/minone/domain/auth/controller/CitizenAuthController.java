package kdt.minone.domain.auth.controller;

import jakarta.validation.Valid;
import kdt.minone.domain.auth.dto.CitizenLoginReqDto;
import kdt.minone.domain.auth.dto.CitizenLoginResDto;
import kdt.minone.domain.auth.dto.CitizenSignupReqDto;
import kdt.minone.domain.auth.dto.CitizenSignupResDto;
import kdt.minone.domain.auth.service.CitizenAuthService;
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
@RequestMapping("/api/v1/auth")
public class CitizenAuthController {

    private final CitizenAuthService citizenAuthService;

    @PostMapping("/signup")
    public ResponseEntity<BaseResDto<CitizenSignupResDto>> signup(
            @Valid @RequestBody CitizenSignupReqDto dto
    ) {

        CitizenSignupResDto result = citizenAuthService.signup(
                dto.getEmail(),
                dto.getPassword(),
                dto.getName(),
                dto.getPhone()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "signup success",
                result
        ), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResDto<CitizenLoginResDto>> login(
            @Valid @RequestBody CitizenLoginReqDto dto
    ) {

        CitizenLoginResDto result = citizenAuthService.login(
                dto.getEmail(),
                dto.getPassword()
        );

        return new ResponseEntity<>(new BaseResDto<>(
                "login success",
                result
        ), HttpStatus.OK);
    }
}
