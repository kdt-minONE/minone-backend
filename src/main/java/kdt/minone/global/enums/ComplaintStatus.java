package kdt.minone.global.enums;

public enum ComplaintStatus {

    ASSIGNED("접수"),
    DEPARTMENT_ASSIGNED("부서 배정"),
    OFFICIAL_ASSIGNED("담당자 배정"),
    COMPLETED("완료"),
    CANCELLED("접수 취소");

    private final String description;

    ComplaintStatus(String description) {
        this.description = description;
    }
}
