package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "man_hour_pricing")
public class ManHourPricing {
    @Id
    @Column(name = "service_category_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int serviceCategoryId;

    @OneToOne
    @JoinColumn(name = "technician_category_id", nullable = false)
    private TechnicianCategory technicianCategory;

    @Column(name = "price_per_hour", nullable = false)
    private BigDecimal pricePerHour;
}
