package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "bill_entry")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bill_entry_id", length = 45)
    private int billEntryId;

    @ManyToOne
    @JoinColumn(name = "bill_id", nullable = false)
    private Bill bill;

    @Column(name = "bill_entry_date", nullable = false)
    private LocalDate billEntryDate;

    @Column(name = "bill_entry_time", nullable = false)
    private LocalTime billEntryTime;

    @Column(name = "service_or_product", length = 254, nullable = false)
    private String serviceOrProduct;

    @Column(name = "quantity", length = 45)
    private int quantity;

    @Column(name = "total_price", nullable = false)
    private BigDecimal totalPrice;
}
