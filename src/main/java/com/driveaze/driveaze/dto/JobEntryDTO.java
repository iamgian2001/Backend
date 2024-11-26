package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.JobRegistry;
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
public class JobEntryDTO {
    private int jobEntryId;
    private JobRegistry jobRegistry;
    private LocalDate entryDate;
    private LocalTime time;
    private int technicianId;
    private String details;
    private BigDecimal manHours;
    private Object inventoryItemList;
}
