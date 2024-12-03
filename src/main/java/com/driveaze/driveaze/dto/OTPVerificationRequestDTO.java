package com.driveaze.driveaze.dto;

import lombok.Data;

@Data
public class OTPVerificationRequestDTO {
    private String phoneNumber;
    private String otp;
    private Long userId;
}