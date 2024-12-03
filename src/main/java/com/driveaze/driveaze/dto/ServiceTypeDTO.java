package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypeDTO {
    private int serviceId;
    private String serviceName;
    private LocalDate registeredDate;
    private LocalTime registeredTime;
}
