package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface CustomerVehicleRepo extends JpaRepository<CustomerVehicle, Integer> {
}
