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
@AllArgsConstructor
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
}
