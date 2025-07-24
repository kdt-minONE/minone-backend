package kdt.minone.domain.complaint.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ComplaintMemoUpdateReqDto {

    private final String title;
    private final String content;
}
