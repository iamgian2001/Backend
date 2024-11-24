package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.entity.Complaint;
import com.driveaze.driveaze.repository.ComplaintRepo;
import com.driveaze.driveaze.service.CustomerComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CustomerComplaintServiceIMPL implements CustomerComplaintService {
    @Autowired
    private ComplaintRepo complaintRepo;

    @Override
    public void addComplaint(ComplaintDTO complaintDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();
        Complaint complaint = new Complaint(
                complaintDTO.getComplaintId(),
                complaintDTO.getCustomerEmail(),
                complaintDTO.getDescription(),
                complaintDTO.getDate(),
                complaintDTO.getStatus()
        );

        complaint.setcustomerEmail(loggedInUsername);

        if(!complaintRepo.existsById(complaint.getComplaintId())){
            complaintRepo.save(complaint);
        }else{
            System.out.println("complaint already exists");
        }
    }

    @Override
    public List<ComplaintDTO> retrieveComplaints() { // Renamed for clarity
        List<Complaint> complaints = complaintRepo.findAll();
        List<ComplaintDTO> complaintDTOs = new ArrayList<>();

        // Convert Complaint entities to ComplaintDTOs
        for (Complaint complaint : complaints) {
            ComplaintDTO complaintDTO = new ComplaintDTO(
                    complaint.getComplaintId(),
                    complaint.getCustomerEmail(),
                    complaint.getDescription(),
                    complaint.getDate(),
                    complaint.getStatus()
            );
            complaintDTOs.add(complaintDTO);
        }

        return complaintDTOs;
    }

    @Override
    public String updateComplaint(ComplaintDTO complaintDTO) {
        if(complaintRepo.existsById(complaintDTO.getComplaintId())){
            Complaint complaint = complaintRepo.getReferenceById(complaintDTO.getComplaintId());
            complaint.setStatus(complaintDTO.getStatus());

            complaintRepo.save(complaint);

            return "updated sucessfully";
        }else{
            return "Unsucessful update";
        }
    }


}
