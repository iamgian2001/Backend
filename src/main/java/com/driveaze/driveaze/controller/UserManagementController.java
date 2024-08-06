package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ReqRes;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.service.UsersManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserManagementController {
    @Autowired
    private UsersManagementService userManagementService;

    @PostMapping("/auth/employee-register")
    public ResponseEntity<ReqRes> employeeRegister(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.employeeRegister(req));
    }

    @PostMapping("/auth/customer-register")
    public ResponseEntity<ReqRes> customerRegister(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.customerRegister(req));
    }

    @PostMapping("/auth/login")
    public ResponseEntity<ReqRes> login(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.login(req));
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<ReqRes> refreshToken(@RequestBody ReqRes req) {
        return ResponseEntity.ok(userManagementService.refreshToken(req));
    }

    @GetMapping("/admin/get-all-employees")
    public ResponseEntity<ReqRes> getAllEmployees() {
        return ResponseEntity.ok(userManagementService.getAllEmployees());
    }

    @GetMapping("/admin/get-all-customers")
    public ResponseEntity<ReqRes> getAllCustomers() {
        return ResponseEntity.ok(userManagementService.getAllCustomers());
    }

    @GetMapping("/admin/get-user/{userId}")
    public ResponseEntity<ReqRes> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userManagementService.getUsersById(userId));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ReqRes> updateUser(@PathVariable Integer userId, @RequestBody OurUsers reqres) {
        return ResponseEntity.ok(userManagementService.updateUser(userId, reqres));
    }

    @GetMapping("/anyuser/get-profile")
    public ResponseEntity<ReqRes> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ReqRes response = userManagementService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ReqRes> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userManagementService.deleteUser(userId));
    }
}
