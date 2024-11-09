package com.driveaze.driveaze.dto;

import com.driveaze.driveaze.entity.TechnicianCategory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ManHourPricingDTO {
    private int serviceCategoryId;
    private TechnicianCategory technicianCategory;
    private BigDecimal pricePerHour;
}
