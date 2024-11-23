package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.SupplierInvoice;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInvoiceEntryDTO {
    private int invoiceEntryId;
    private SupplierInvoice supplierInvoice;
    private LocalDate invoiceEntryDate;
    private LocalTime invoiceEntryTime;
    private String item;
    private int quantity;
    private BigDecimal price;
}
