package com.driveaze.driveaze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehicleDTO {

    private int vehicleId;
    private String vehicleNo;
    private String brand;
    private String model;
    private int customerId;

}
