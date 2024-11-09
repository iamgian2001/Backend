package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.Supplier;
import com.driveaze.driveaze.entity.SupplierInvoiceEntry;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInvoiceDTO {
    private int invoiceId;
    private Supplier supplier;
    private LocalDate invoiceDate;
    private LocalTime invoiceTime;
    private int invoiceStatus;
    private List<SupplierInvoiceEntry> entries;
}
