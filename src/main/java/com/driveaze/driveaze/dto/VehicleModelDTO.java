package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.VehicleBrand;
import com.driveaze.driveaze.entity.VehicleModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleModelDTO {
    private Long modelId;
    private Long brandId;
    private String modelName;
    private String fuelType;
    private LocalDate registeredDate;
}
