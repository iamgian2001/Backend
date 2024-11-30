package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_entry_item")
public class JobEntryItem {
    @Id
    @Column(name = "job_entry_item_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobEntryItemId;

    @ManyToOne
    @JoinColumn(name = "job_entry_id", nullable = false)
    private JobEntry jobEntry;

    @Column(name = "date", length =100)
    private LocalDate date;

    @Column(name = "time", length =100)
    private LocalTime time;

    @Column(name = "item_name", length =100)
    private String itemName;

    @Column(name = "quantity", length =100)
    private int quantity;
}
