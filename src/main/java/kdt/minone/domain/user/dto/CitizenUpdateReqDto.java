package kdt.minone.domain.user.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CitizenUpdateReqDto {

    private final String oldPassword;
    private final String newPassword;
}
