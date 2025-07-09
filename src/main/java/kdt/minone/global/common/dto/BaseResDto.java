package kdt.minone.global.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BaseResDto<T extends BaseDtoDataType> {

    private final String message;
    private final T data;
}
