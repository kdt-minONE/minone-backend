package kdt.minone.domain.auth.dto;

import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CitizenLoginResDto implements BaseDtoDataType {

    private final Long id;
    private final String accessToken;
    private final String refreshToken;
    private final String email;
    private final String name;
    private final String role;
}
