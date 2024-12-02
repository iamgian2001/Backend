package com.driveaze.driveaze.dto;
import java.sql.Time;
import java.util.Date;
public class BookingDTO {
    private int bookingId;
    private String vehicleNo;
    private String brand;
    private String model;
    private String status = "waiting";;
    private Date preferredDate;
    private Time preferredTime;
    private Long customerId;

    public BookingDTO() {
    }

    public BookingDTO(int bookingId, String vehicleNo, String brand, String model, String status, Date preferredDate, Time preferredTime, Long customerId) {
        this.bookingId = bookingId;
        this.vehicleNo = vehicleNo;
        this.brand = brand;
        this.model = model;
        this.status = status;
        this.preferredDate = preferredDate;
        this.preferredTime = preferredTime;
        this.customerId = customerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getStatus() {
        return status;
    }

    public Date getPreferredDate() {
        return preferredDate;
    }

    public Time getPreferredTime() {
        return preferredTime;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPreferredDate(Date preferredDate) {
        this.preferredDate = preferredDate;
    }

    public void setPreferredTime(Time preferredTime) {
        this.preferredTime = preferredTime;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "BookingDTO{" +
                "bookingId=" + bookingId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", status='" + status + '\'' +
                ", preferredDate=" + preferredDate +
                ", preferredTime=" + preferredTime +
                ", customerId=" + customerId +
                '}';
    }
}