package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.ComplaintDTO;

import java.util.List;

public interface CustomerComplaintService {

    void addComplaint(ComplaintDTO complaintDTO);

    public List<ComplaintDTO> retrieveComplaints();

    String updateComplaint(ComplaintDTO complaintDTO);

}
