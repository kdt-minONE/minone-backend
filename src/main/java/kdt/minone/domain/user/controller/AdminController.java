package kdt.minone.domain.user.controller;

import kdt.minone.domain.user.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/master/employees")
public class AdminController {

    private final AdminService adminService;

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Void> deleteEmployee(
            @PathVariable Long employeeId
    ) {

        adminService.deleteEmployee(employeeId);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
