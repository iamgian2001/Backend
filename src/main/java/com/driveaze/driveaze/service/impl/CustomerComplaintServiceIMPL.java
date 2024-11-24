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
        // Get the currently authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String loggedInUsername = authentication.getName();

        // Create a new Complaint, with the complaintId excluded
        Complaint complaint = new Complaint(
                complaintDTO.getCustomerEmail(),
                complaintDTO.getDescription(),
                complaintDTO.getDate(),
                complaintDTO.getStatus()
        );

        // Set the logged-in user's email as the customerEmail
        complaint.setcustomerEmail(loggedInUsername);

        // Save the complaint to the repository (complaintId will be auto-generated)
        complaintRepo.save(complaint);
    }

    @Override
    public List<ComplaintDTO> retrieveComplaints() {
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
        if (complaintRepo.existsById(complaintDTO.getComplaintId())) {
            Complaint complaint = complaintRepo.getReferenceById(complaintDTO.getComplaintId());
            complaint.setStatus(complaintDTO.getStatus());

            complaintRepo.save(complaint);

            return "Updated successfully";
        } else {
            return "Unsuccessful update";
        }
    }
}
