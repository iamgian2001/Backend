package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    @Column(name = "service_name", length =100, nullable = false)
    private String serviceName;
}
