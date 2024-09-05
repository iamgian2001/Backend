package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.auth.LoginRequest;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.service.interfac.IUserManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserManagementService userService;

    @PostMapping("/employee-register")
    public ResponseEntity<ResponseDTO> employeeRegister(@RequestBody OurUsers request) {
        return ResponseEntity.ok(userService.employeeRegister(request));
    }

    @PostMapping("/customer-register")
    public ResponseEntity<ResponseDTO> customerRegister(@RequestBody OurUsers request) {
        return ResponseEntity.ok(userService.customerRegister(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(userService.login(loginRequest));
    }
}
