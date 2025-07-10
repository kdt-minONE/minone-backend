package kdt.minone.domain.auth.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginReqDto {

    @NotBlank
    private final String email;

    @NotBlank
    private final String password;
}
