package kdt.minone.global.common.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class BaseListResDto<T extends BaseDtoDataType> {

    private final String message;
    private final List<T> data;
}
