package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "complaint_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int complaintId;

    @Column(name = "description", length =500, nullable = false)
    private String description;

    @Column(name = "status", length = 1)
    private int status;

    public Complaint() {
    }

    public Complaint(int complaintId, String description, int status) {
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


