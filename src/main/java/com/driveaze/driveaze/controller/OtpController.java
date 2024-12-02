package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.service.impl.NotifyServiceIMPL;
import com.driveaze.driveaze.service.interfac.NotifyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class OtpController {

    @Autowired
    private NotifyServiceIMPL notifyService;

    // Endpoint to send OTP
    @PostMapping("/send")
    public String sendOtp(@RequestParam String phoneNumber) {
        notifyService.sendOtp(phoneNumber);
        return "OTP sent to " + phoneNumber;
    }

    // Endpoint to verify OTP
    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String phoneNumber, @RequestParam String otp) {
        boolean isValid = notifyService.verifyOtp(phoneNumber, otp);
        return isValid ? "OTP verified successfully!" : "Invalid OTP!";
    }
}
