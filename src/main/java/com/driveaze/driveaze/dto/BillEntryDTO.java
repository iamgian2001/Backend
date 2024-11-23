package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Time;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillEntryDTO {
    private int billEntryId;
    private Bill bill;
    private LocalDate billEntryDate;
    private Time billEntryTime;
    private String serviceOrProduct;
    private int quantity;
    private BigDecimal totalPrice;
}
