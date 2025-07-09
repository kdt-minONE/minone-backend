package kdt.minone.domain.auth.dto;

import kdt.minone.domain.user.entity.Citizen;
import kdt.minone.global.common.dto.BaseDtoDataType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CitizenSignupResDto implements BaseDtoDataType {
    private final Long id;
    private final String email;
    private final String name;
    private final String phone;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public CitizenSignupResDto(Citizen citizen) {
        this.id = citizen.getId();
        this.email = citizen.getEmail();
        this.name = citizen.getName();
        this.phone = citizen.getPhone();
        this.createdAt = citizen.getCreatedAt();
        this.updatedAt = citizen.getUpdatedAt();
    }
}
