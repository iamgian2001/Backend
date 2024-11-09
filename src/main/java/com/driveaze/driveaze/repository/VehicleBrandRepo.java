package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.VehicleBrand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface VehicleBrandRepo extends JpaRepository<VehicleBrand, Integer> {
    boolean existsByBrandName(String brandName);

    Optional<VehicleBrand> findByBrandName(String brandName);
}
