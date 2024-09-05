package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.sql.Date;

public class ComplaintDTO {

    private int complaintId;
    private String description;
    private java.sql.Date date;
    private int status;

    public ComplaintDTO() {
    }

    public ComplaintDTO(int complaintId, String description, Date date, int status) {
        this.complaintId = complaintId;
        this.description = description;
        this.date = date;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }


}
