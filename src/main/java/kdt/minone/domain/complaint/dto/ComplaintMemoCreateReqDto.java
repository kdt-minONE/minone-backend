package kdt.minone.domain.complaint.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComplaintMemoCreateReqDto {

    @NotBlank
    private final String title;

    @NotBlank
    private final String content;
}
