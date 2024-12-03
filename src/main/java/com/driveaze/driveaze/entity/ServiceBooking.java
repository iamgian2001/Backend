package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Table(name = "service_booking")
@Data
@NoArgsConstructor
public class ServiceBooking {
    @Id
    @Column(name = "booking_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookingId;

    @Column(name = "vehicle_no", length =100, nullable = false)
    private String vehicleNo;

    @Column(name = "vehicle_brand", length = 100)
    private String vehicleBrand;

    @Column(name = "vehicle_model", length = 100)
    private String vehicleModel;

    @Column(name = "booking_date", length =100)
    private LocalDate startedDate;

    @Column(name = "booking_time", length =100)
    private Time startTime;

    @Column(name = "customer_id", length = 100)
    private int customerId;

    public ServiceBooking(int bookingId, String vehicleNo, String vehicleBrand, String vehicleModel, LocalDate startedDate, Time startTime, int customerId) {
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
        return "ServiceBooking{" +
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
