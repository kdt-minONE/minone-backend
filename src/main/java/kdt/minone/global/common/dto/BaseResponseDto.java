package kdt.minone.global.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseResponseDto <T extends BaseDtoDataType> {
    private final String success;
    private final String status;
    private final String code;
    private final String message;
    private final T data;
}
