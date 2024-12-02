package com.driveaze.driveaze.service.interfac;

public interface OtpService {

    // Method to generate OTP for a given mobile number
    String generateOtp(String mobileNumber);

    // Method to validate the OTP for a given mobile number
    boolean validateOtp(String mobileNumber, String otp);

    // Method to send the OTP via Notify.lk SMS gateway
    void sendOtp(String mobileNumber, String otp);
}
