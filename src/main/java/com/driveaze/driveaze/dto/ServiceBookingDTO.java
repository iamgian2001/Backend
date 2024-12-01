package com.driveaze.driveaze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class ServiceBookingDTO {
    private int bookingId;
    private String vehicleNo;
    private String vehicleBrand;
    private String vehicleModel;
    private LocalDate startedDate;
    private Time startTime;
    private int customerId;

    public ServiceBookingDTO(int bookingId, String vehicleNo, String vehicleBrand, String vehicleModel, LocalDate startedDate, Time startTime, int customerId) {
        this.bookingId = bookingId;
        this.vehicleNo = vehicleNo;
        this.vehicleBrand = vehicleBrand;
        this.vehicleModel = vehicleModel;
        this.startedDate = startedDate;
        this.startTime = startTime;
        this.customerId = customerId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public String getVehicleBrand() {
        return vehicleBrand;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public LocalDate getStartedDate() {
        return startedDate;
    }

    public Time getStartTime() {
        return startTime;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public void setVehicleBrand(String vehicleBrand) {
        this.vehicleBrand = vehicleBrand;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public void setStartedDate(LocalDate startedDate) {
        this.startedDate = startedDate;
    }

    public void setStartTime(Time startTime) {
        this.startTime = startTime;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ServiceBookingDTO{" +
                "bookingId=" + bookingId +
                ", vehicleNo='" + vehicleNo + '\'' +
                ", vehicleBrand='" + vehicleBrand + '\'' +
                ", vehicleModel='" + vehicleModel + '\'' +
                ", startedDate=" + startedDate +
                ", startTime=" + startTime +
                ", customerId=" + customerId +
                '}';
    }
}
