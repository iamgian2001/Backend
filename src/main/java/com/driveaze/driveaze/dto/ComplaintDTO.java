package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ComplaintDTO {

    private int complaintId;
    private String description;
    private int status;

    public ComplaintDTO() {
    }

    public ComplaintDTO(int complaintId, String description, int status) {
        this.complaintId = complaintId;
        this.description = description;
        this.status = status;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public String getDescription() {
        return description;
    }

    public int getStatus() {
        return status;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
