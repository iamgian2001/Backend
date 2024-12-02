package com.driveaze.driveaze.service.interfac;

public interface NotifyService {
    void sendOtp(String phoneNumber);
    boolean verifyOtp(String phoneNumber, String otp);
}
