package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "supplier")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Supplier {
    @Id
    @Column(name = "supplier_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;

    @Column(name = "supplier_email", length = 100, nullable = false)
    private String supplierEmail;

    @Column(name = "contact_number", length = 100, nullable = false)
    private String contactNumber;

    @Column(name = "supplier_name", length = 100, nullable = false)
    private String supplierName;

    @Column(name = "address", length = 254, nullable = false)
    private String address;

    @Column(name = "parts_description", length = 254, nullable = false)
    private String partsDescription;

    @Column(name = "registered_date", length =100, nullable = false)
    private LocalDate registeredDate;
}
