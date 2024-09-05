package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_registry")
public class JobRegistry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_id", length =100, nullable = false)
    private int vehicleId;

    @Column(name = "started_date", length =100)
    private LocalDate startedDate;

    @Column(name = "start_time", length =100)
    private Time startTime;

    @Column(name = "finished_date", length =100)
    private LocalDate finishedDate;

    @Column(name = "customer_id", length =100)
    private int customerId;

    @Column(name = "supervisor_id", length =100)
    private int supervisorId;

    @Column(name = "job_description", length =255)
    private String jobDescription;

}
