package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.VehicleBrand;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModelDTO {
    private Long modelId;
    private VehicleBrand vehicleBrand;
    private String fuelType;
    private LocalDate registeredDate;
}
