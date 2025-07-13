package kdt.minone.global.enums;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public enum EmployeeRole {

    EMPLOYEE("민원 처리 담당자"),
    MANAGER("부서 담당자"),
    ADMIN("관리자");

    public static EmployeeRole of(String name) {
        for (EmployeeRole role : values()) {
            if (role.name().equals(name)) {
                return role;
            }
        }

        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "not found");
    }

    private final String description;

    EmployeeRole(String description) {
        this.description = description;
    }
}
