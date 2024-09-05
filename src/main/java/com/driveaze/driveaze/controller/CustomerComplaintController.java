package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.dto.CustomerVehicleDTO;
import com.driveaze.driveaze.service.CustomerComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer-complaint")
@CrossOrigin
public class CustomerComplaintController {

    @Autowired
    private CustomerComplaintService customerComplaintService;

    @PostMapping(path = "/save")
    public String saveComplaint(@RequestBody ComplaintDTO complaintDTO){
        customerComplaintService.addComplaint(complaintDTO);
        return "saved";
    }

    @GetMapping(path = "/retrieve")
    public ResponseEntity<List<ComplaintDTO>> retrieveComplaints() {
        List<ComplaintDTO> complaintDTOs = customerComplaintService.retrieveComplaints();
        return ResponseEntity.ok(complaintDTOs);
    }
}
