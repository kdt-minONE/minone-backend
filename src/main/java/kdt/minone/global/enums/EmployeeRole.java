package kdt.minone.global.enums;

public enum EmployeeRole {

    EMPLOYEE("민원 처리 담당자"),
    MANAGER("부서 담당자"),
    ADMIN("관리자");

    private final String description;

    EmployeeRole(String description) {
        this.description = description;
    }
}
