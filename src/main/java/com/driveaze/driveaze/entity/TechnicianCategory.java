package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
}
