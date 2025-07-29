package kdt.minone.global.enums;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum ComplaintStatus {

    ASSIGNED("접수"),
    DEPARTMENT_ASSIGNED("부서 배정"),
    OFFICIAL_ASSIGNED("담당자 배정"),
    COMPLETED("완료"),
    CANCELLED("접수 취소");

    public static ComplaintStatus of(String name) {
        for (ComplaintStatus status : values()) {
            if (status.name().equals(name)) {
                return status;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
    }

    private final String description;

    ComplaintStatus(String description) {
        this.description = description;
    }
}
