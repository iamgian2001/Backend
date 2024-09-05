package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CustomerVehicleRepo extends JpaRepository<CustomerVehicle, Integer> {

    boolean existsByVehicleNo(String vehicleNo);
    Optional<CustomerVehicle> findByVehicleNo(String vehicleNo);
}
