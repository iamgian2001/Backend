package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.service.interfac.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserManagementController {
    @Autowired
    private IUserManagementService userService;

    @GetMapping("/admin/get-all-employees")
    public ResponseEntity<ResponseDTO> getAllEmployees() {
        return ResponseEntity.ok(userService.getAllEmployees());
    }

    @GetMapping("/admin/get-all-customers")
    public ResponseEntity<ResponseDTO> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/admin/get-user/{userId}")
    public ResponseEntity<ResponseDTO> getUsersById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUsersById(userId));
    }

    @PutMapping("/admin/update/{userId}")
    public ResponseEntity<ResponseDTO> updateUser(@PathVariable Integer userId, @RequestBody OurUsers request) {
        return ResponseEntity.ok(userService.updateUser(userId, request));
    }

    @GetMapping("/anyuser/get-profile")
    public ResponseEntity<ResponseDTO> getMyProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        ResponseDTO response = userService.getMyInfo(email);
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @DeleteMapping("/admin/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }
}
