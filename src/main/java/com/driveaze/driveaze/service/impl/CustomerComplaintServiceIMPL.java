package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.entity.Complaint;
import com.driveaze.driveaze.repository.ComplaintRepo;
import com.driveaze.driveaze.service.CustomerComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CustomerComplaintServiceIMPL implements CustomerComplaintService {
    @Autowired
    private ComplaintRepo complaintRepo;

    @Override
    public void addComplaint(ComplaintDTO complaintDTO) {
        Complaint complaint = new Complaint(
                complaintDTO.getComplaintId(),
                complaintDTO.getDescription(),
                complaintDTO.getStatus()
        );

        if(!complaintRepo.existsById(complaint.getComplaintId())){
            complaintRepo.save(complaint);
        }else{
            System.out.println("complaint already exists");
        }
    }
}
