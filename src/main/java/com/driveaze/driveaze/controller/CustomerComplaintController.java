package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.service.CustomerComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
