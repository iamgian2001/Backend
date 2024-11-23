package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierDTO {
    private Long supplierId;
    private String supplierEmail;
    private String contactNumber;
    private String supplierName;
    private String address;
    private String partsDescription;
    private LocalDate registeredDate;
}
