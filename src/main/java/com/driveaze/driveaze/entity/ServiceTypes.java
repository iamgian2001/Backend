package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name = "service_types")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceTypes {
    @Id
    @Column(name = "service_id", length =45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int serviceId;

    @Column(name = "service_name", length =100,  nullable = false)
    private String serviceName;

    @Column(name = "registered_date", length =100)
    private LocalDate registeredDate;

    @Column(name = "registered_time", length =100)
    private LocalTime registeredTime;
}
