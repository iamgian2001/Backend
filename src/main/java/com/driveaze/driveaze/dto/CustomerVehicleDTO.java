package com.driveaze.driveaze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerVehicleDTO {

    private int vehicleId;
    private String vehicleNo;
    private String ownerName;
    private String ownerEmail;
    private String ownerPhone;
    private Integer vehicleMilage;
    private int vehicleBrandId;
    private int vehicleModelId;
    private int customerId;
    private LocalDate registeredDate;
    private Time registeredTime;

}
