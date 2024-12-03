package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "technician_category")
public class TechnicianCategory {
    @Id
    @Column(name = "technician_category_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int technicianCategoryId;

    @Column(name = "technician_category_name", length =255)
    private String technicianCategoryName;

    @Column(name = "registration_date", length =255)
    private LocalDate registrationDate;

    @Column(name = "price_per_man_hour", length =255)
    private BigDecimal pricePerManHour;
}
