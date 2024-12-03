package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "complaint_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int complaintId;

    @Column(name = "customer_email",length = 500)
    private String customerEmail;

    @Column(name = "description", length =1000, nullable = false)
    private String description;

    @Column(name = "reply", length = 1000)
    private String reply;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "status", length = 1)
    private int status;

    public Complaint() {
    }

    public Complaint(String customerEmail, String description, LocalDate date, int status) {
        this.complaintId = complaintId;
        this.customerEmail = customerEmail;
        this.description = description;
        this.date = date;
        this.status = status;
    }



    public String getDescription() {
        return description;
    }

    public String getReply() {return reply;}

    public int getComplaintId() {
        return complaintId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public int getStatus() {
        return status;
    }

    public void setComplaintId(int complaintId) {
        this.complaintId = complaintId;
    }

    public void setReply(String reply) {this.reply = reply;}

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "complaintId=" + complaintId +
                ", customerEmail='" + customerEmail + '\'' +
                ", description='" + description + '\'' +
                ", reply='" + reply + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }
}


