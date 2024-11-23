package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.ServiceTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ServiceTypeRepo extends JpaRepository<ServiceTypes, Integer>{

    boolean existsByServiceName(String serviceName);
}