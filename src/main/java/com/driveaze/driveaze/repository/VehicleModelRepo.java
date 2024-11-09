package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.VehicleModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface VehicleModelRepo extends JpaRepository<VehicleModel, Integer> {

    boolean existsByModelName(String modelName);

    Optional<VehicleModel> findByModelName(String modelName);
}
