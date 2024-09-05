package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.dto.auth.LoginRequest;
import com.driveaze.driveaze.entity.OurUsers;
import com.driveaze.driveaze.service.interfac.IUserService;
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
    private IUserService userService;

    @PostMapping("/employee-register")
    public ResponseEntity<ResponseDTO> employeeRegister(@RequestBody OurUsers user) {
        ResponseDTO responseDTO = userService.employeeRegister(user);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

    @PostMapping("/customer-register")
    public ResponseEntity<ResponseDTO> customerRegister(@RequestBody OurUsers user) {
        ResponseDTO responseDTO = userService.customerRegister(user);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody LoginRequest loginRequest) {
        ResponseDTO responseDTO = userService.login(loginRequest);
        return ResponseEntity.status(responseDTO.getStatusCode()).body(responseDTO);
    }
}
