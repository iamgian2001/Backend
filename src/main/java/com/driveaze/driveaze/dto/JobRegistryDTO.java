package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobRegistryDTO {

    private int id;
    private int vehicleId;
    private LocalDate startedDate;
    private Time startTime;
    private LocalDate finishedDate;
    private int customerId;
    private int supervisorId;
    private int jobStatus;
    private String jobDescription;
}
