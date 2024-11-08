package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface JobRegistryRepo extends JpaRepository<JobRegistry, Integer> {

    Optional<JobRegistry> findByVehicleId(int vehicleId);

    boolean existsByVehicleIdAndJobStatus(int vehicleId, int jobStatus);
}
