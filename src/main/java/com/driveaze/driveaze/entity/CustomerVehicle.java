package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "customer_vehicle")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehicle {

    @Id
    @Column(name = "vehicle_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int vehicleId;

    @Column(name = "vehicle_no", length =100, nullable = false)
    private String vehicleNo;

    @Column(name = "vehicleMilage", length = 100)
    private Integer vehicleMilage;

    @Column(name = "vehicle_brand_id", length = 100)
    private int vehicleBrandId;

    @Column(name = "vehicle_model_id", length = 100)
    private int vehicleModelId;

    @Column(name = "customer_id", length = 100)
    private int customerId;

    @Column(name = "registered_date", length =100)
    private LocalDate registeredDate;

    @Column(name = "registered_time", length =100)
    private LocalTime registeredTime;

}
