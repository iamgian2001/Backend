package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.service.CustomerComplaintService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-complaint")
@CrossOrigin
public class CustomerComplaintController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerComplaintController.class);

    @Autowired
    private CustomerComplaintService customerComplaintService;

    /**
     * Save a complaint.
     */
    @PostMapping(path = "/save")
    public ResponseEntity<?> saveComplaint(@RequestBody ComplaintDTO complaintDTO) {
        try {
            logger.info("Saving complaint: {}", complaintDTO);
            customerComplaintService.addComplaint(complaintDTO);
            return ResponseEntity.status(201).body("Complaint successfully saved!");
        } catch (Exception e) {
            logger.error("Error saving complaint: ", e);
            return ResponseEntity.status(500).body("Failed to save complaint: " + e.getMessage());
        }
    }

    /**
     * Retrieve all complaints.
     */
    @GetMapping(path = "/retrieve")
    public ResponseEntity<?> retrieveComplaints() {
        try {
            logger.info("Retrieving all complaints...");
            List<ComplaintDTO> complaintDTOs = customerComplaintService.retrieveComplaints();
            if (complaintDTOs.isEmpty()) {
                logger.info("No complaints found.");
                return ResponseEntity.status(204).body("No complaints found.");
            }
            return ResponseEntity.ok(complaintDTOs);
        } catch (Exception e) {
            logger.error("Error retrieving complaints: ", e);
            return ResponseEntity.status(500).body("Error retrieving complaints: " + e.getMessage());
        }
    }

    /**
     * Update a complaint.
     */
    @PutMapping(path = "/update")
    public ResponseEntity<?> updateComplaint(@RequestBody ComplaintDTO complaintDTO) {
        try {
            logger.info("Updating complaint: {}", complaintDTO);
            String result = customerComplaintService.updateComplaint(complaintDTO);
            return ResponseEntity.ok(result); // Assuming the service returns a success message
        } catch (Exception e) {
            logger.error("Error updating complaint: ", e);
            return ResponseEntity.status(500).body("Failed to update complaint: " + e.getMessage());
        }
    }
}
