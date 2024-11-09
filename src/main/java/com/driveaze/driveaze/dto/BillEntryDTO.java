package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.Bill;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillEntryDTO {
    private int billEntryId;
    private Bill bill;
    private LocalDate billEntryDate;
    private LocalTime billEntryTime;
    private String serviceOrProduct;
    private int quantity;
    private BigDecimal totalPrice;
}
