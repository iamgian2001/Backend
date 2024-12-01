package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.JobEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobEntryItemDTO {
    private int jobEntryItemId;
    private JobEntry jobEntry;
    private LocalDate date;
    private LocalTime time;
    private String itemName;
    private int quantity;
}
