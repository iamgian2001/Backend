package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "complaint_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int complaintId;

    @Column(name = "description", length =500, nullable = false)
    private String description;

    @Column(name = "date")
    private java.sql.Date date;

    @Column(name = "status", length = 1)
    private int status;

    public Complaint() {
    }

    public Complaint(int complaintId, String description, Date date, int status) {
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


