package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.service.impl.UserManagementService;
import com.driveaze.driveaze.service.interfac.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class UserManagementController {
    @Autowired
    private IUserManagementService userService;
    @Autowired
    private UserManagementService userManagementService;

    @GetMapping("/superuser/get-all-employees")
    public ResponseEntity<ResponseDTO> getAllEmployees() {
        return ResponseEntity.ok(userService.getAllEmployees());
    }

    //without managers and admins
    @GetMapping("/superuser/get-all-staff")
    public ResponseEntity<ResponseDTO> getAllStaff() {
        return ResponseEntity.ok(userService.getAllStaff());
    }

    @GetMapping("/superuser/get-all-customers")
    public ResponseEntity<ResponseDTO> getAllCustomers() {
        return ResponseEntity.ok(userService.getAllCustomers());
    }

    @GetMapping("/superuser/get-user/{userId}")
    public ResponseEntity<ResponseDTO> getUsersById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUsersById(userId));
    }

    @PutMapping("/superuser/update/{userId}")
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

    @DeleteMapping("/superuser/delete/{userId}")
    public ResponseEntity<ResponseDTO> deleteUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @GetMapping("/superuser/search-supervisors")
    public List<OurUsers> serachSupervisors(@RequestParam("query") String query) {
        return userManagementService.searchBySupervisorName(query);
    }
}
