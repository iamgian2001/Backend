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
@Table(name = "job_entry")
public class JobEntry {
    @Id
    @Column(name = "job_entry_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobEntryId;

    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false)
    private JobRegistry jobRegistry;

    @Column(name = "entry_date", length =100)
    private LocalDate entryDate;

    @Column(name = "time", length =100)
    private LocalTime time;

    @Column(name = "technician_id", length =100)
    private int technicianId;

    @Column(name = "details", length =255)
    private String details;

    @Column(name = "man_hours", nullable = false)
    private BigDecimal manHours;
}
