package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.ManHourPricing;
import com.driveaze.driveaze.entity.TechnicianCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface ManHourPricingRepo extends JpaRepository<ManHourPricing, Integer> {

    boolean existsByTechnicianCategory(TechnicianCategory technicianCategory);

    Optional<ManHourPricing> findByTechnicianCategory(TechnicianCategory technicianCategory);
}
