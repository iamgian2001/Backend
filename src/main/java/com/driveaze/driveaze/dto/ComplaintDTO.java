package com.driveaze.driveaze.dto;

import java.sql.Date;

public class ComplaintDTO {

    private int complaintId;
    private String customerEmail;
    private String description;
    private java.sql.Date date;
    private int status;

    public ComplaintDTO() {
    }

    public ComplaintDTO(int complaintId,String customerEmail, String description, Date date, int status) {
        this.complaintId = complaintId;
        this.customerEmail = customerEmail;
        this.description = description;
        this.date = date;
        this.status = status;
    }

    public int getComplaintId() {
        return complaintId;
    }

    public String getCustomerEmail() {
        return customerEmail;
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

    public void setcustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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
        return "ComplaintDTO{" +
                "complaintId=" + complaintId +
                ", customerEmail=" + customerEmail +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", status=" + status +
                '}';
    }


}
