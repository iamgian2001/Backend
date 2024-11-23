package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.JobRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface JobRegistryRepo extends JpaRepository<JobRegistry, Integer> {

    boolean existsByCustomerVehicleAndJobStatus(CustomerVehicle customerVehicle, int i);

}
