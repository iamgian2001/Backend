package com.driveaze.driveaze.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceBookingDTO {
    private int bookingId;
    private String vehicleNo;
    private String vehicleBrand;
    private String vehicleModel;
    private LocalDate startedDate;
    private Time startTime;
    private int customerId;
}
