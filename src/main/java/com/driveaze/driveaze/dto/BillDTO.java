package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.BillEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private int billId;
    private int jobId;
    private LocalDate billDate;
    private Time billTime;
    private int billStatus;
    private List<BillEntry> entries;
}
