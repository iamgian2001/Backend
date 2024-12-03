package com.driveaze.driveaze.dto;

import java.sql.Date;
import java.time.LocalDate;

public class ComplaintDTO {

    private int complaintId;
    private String customerEmail;
    private String description;
    private String reply;
    private LocalDate date;
    private int status;
    private String complaintHolder;

    public ComplaintDTO(int complaintId, String customerEmail, String complaintHolder, String description, String reply, LocalDate date, int status) {
        this.complaintId = complaintId;
        this.customerEmail = customerEmail;
        this.complaintHolder = complaintHolder;
        this.description = description;
        this.reply = reply;
        this.date = date;
        this.status = status;

    }

    public String getReply() {
        return reply;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public String getComplaintHolder() {
        return complaintHolder;
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

    public void setReply(String reply) {
        this.reply = reply;
    }

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

    public void setComplaintHolder(String complaintHolder) {
        this.complaintHolder = complaintHolder;
    }

    @Override
    public String toString() {
        return "ComplaintDTO{" +
                "complaintId=" + complaintId +
                ", customerEmail='" + customerEmail + '\'' +
                ", description='" + description + '\'' +
                ", reply='" + reply + '\'' +
                ", date=" + date +
                ", status=" + status +
                ", complaintHolder='" + complaintHolder + '\'' +
                '}';
    }


}
