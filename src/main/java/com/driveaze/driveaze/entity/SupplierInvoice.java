package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "supplier_invoice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "invoice_id", length = 45)
    private int invoiceId;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "invoice_date", nullable = false)
    private LocalDate invoiceDate;

    @Column(name = "invoice_time", nullable = false)
    private LocalTime invoiceTime;

    //bill Status
    //0 - > pending(Not Finalized)
    //1 - > Completed
    @Column(name = "invoice_status", nullable = false)
    private int invoiceStatus;

    @OneToMany(mappedBy = "supplierInvoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SupplierInvoiceEntry> entries;
}
