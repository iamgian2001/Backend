package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "vehicle_model")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModel {
    @Id
    @Column(name = "model_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long modelId;

    @ManyToOne
    @JoinColumn(name = "brand_id", nullable = false)
    private VehicleBrand vehicleBrand;

    @Column(name = "model_name", length = 254, nullable = false)
    private String modelName;

    @Column(name = "fuel_type", length = 254, nullable = false)
    private String fuelType;

    @Column(name = "registered_date", length =100, nullable = false)
    private LocalDate registeredDate;
}
