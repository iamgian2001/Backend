package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_registry")
public class JobRegistry {

    @Id
    @Column(name = "job_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int jobId;

    @Column(name = "vehicle_id", length =100)
    private int vehicleId;

    @Column(name = "started_date", length =100)
    private LocalDate startedDate;

    @Column(name = "start_time", length =100)
    private LocalTime  startTime;

    @Column(name = "finished_date", length =100)
    private LocalDate finishedDate;

    @Column(name = "supervisor_id", length =100)
    private int supervisorId;

    @Column(name = "service_type_id", length =100)
    private int serviceTypeId;

    @Column(name = "vehicleMilage", length = 100)
    private Integer vehicleMilage;

    //0 -> incomplete
    //1 -> complete
    @Column(name = "job_status", length =100)
    private int jobStatus;

    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

}
