package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "vehicle_brand", length = 100)
    private String vehicleBrand;

    @Column(name = "vehicle_model", length = 100)
    private String vehicleModel;

    @Column(name = "customer_id", length = 100)
    private int customerId;

}
