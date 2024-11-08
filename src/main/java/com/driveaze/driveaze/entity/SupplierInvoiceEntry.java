package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "supplier_invoice_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInvoiceEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_entry_id", length = 45)
    private int invoiceEntryId;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private SupplierInvoice supplierInvoice;

    @Column(name = "invoice_entry_date", nullable = false)
    private LocalDate invoiceEntryDate;

    @Column(name = "invoice_entry_time", nullable = false)
    private LocalTime invoiceEntryTime;

    @Column(name = "item", length = 254, nullable = false)
    private String item;

    @Column(name = "quantity", length = 45)
    private int quantity;

    @Column(name = "price", nullable = false)
    private BigDecimal price;
}
