package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.service.interfac.OtpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class OtpServiceImpl implements OtpService {

    private final Map<String, String> otpStore = new HashMap<>();
    private final RestTemplate restTemplate;

    @Value("${notify.lk.api.key}")
    private String apiKey; // API Key for Notify.lk (can be stored in application.properties)

    @Value("${notify.lk.sender.id}")
    private String senderId; // Sender ID for Notify.lk (can be stored in application.properties)

    @Value("${notify.lk.api.url}")
    private String apiUrl; // API URL for Notify.lk

    public OtpServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String generateOtp(String mobileNumber) {
        // Generate a 6-digit OTP
        String otp = String.valueOf(new Random().nextInt(900000) + 100000);
        otpStore.put(mobileNumber, otp);
        return otp;
    }

    @Override
    public boolean validateOtp(String mobileNumber, String otp) {
        // Validate OTP by checking against stored OTP
        return otp.equals(otpStore.get(mobileNumber));
    }

    @Override
    public void sendOtp(String mobileNumber, String otp) {
        // Send OTP via Notify.lk API
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + apiKey); // Add API key in Authorization header

        // Prepare the request body with necessary parameters
        String message = "Your OTP code is: " + otp;

        String requestBody = "{"
                + "\"user_id\": \"" + apiKey + "\","
                + "\"sender_id\": \"" + senderId + "\","
                + "\"to\": \"" + mobileNumber + "\","
                + "\"message\": \"" + message + "\""
                + "}";

        // Make the API call
        RequestEntity<String> requestEntity = new RequestEntity<>(requestBody, headers, HttpMethod.POST, URI.create(apiUrl));

        // Send the request
        restTemplate.exchange(requestEntity, String.class);
    }
}
