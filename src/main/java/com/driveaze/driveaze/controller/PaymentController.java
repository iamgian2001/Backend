package com.driveaze.driveaze.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @PostMapping("/notify")
    public ResponseEntity<String> handlePaymentNotification(@RequestParam Map<String, String> paymentData) {
        // Log or validate the incoming payment data
        System.out.println("Payment Notification Received: " + paymentData);

        // Verify payment status
        String status = paymentData.get("status_code");
        if ("2".equals(status)) { // 2 indicates success in PayHere
            // Process successful payment (e.g., update database)
            return ResponseEntity.ok("Payment Processed Successfully");
        }

        // Handle failed payment
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment Failed");
    }
}
