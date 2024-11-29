package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.CustomerVehicle;
import com.driveaze.driveaze.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface CustomerVehicleRepo extends JpaRepository<CustomerVehicle, Integer> {

    boolean existsByVehicleNo(String vehicleNo);
    Optional<CustomerVehicle> findByVehicleNo(String vehicleNo);

    @Query("SELECT c FROM CustomerVehicle c WHERE LOWER(c.vehicleNo) LIKE LOWER(CONCAT('%', :vehicleNo, '%'))")
    List<CustomerVehicle> searchByVehicleNo(@Param("vehicleNo") String vehicleNo);

    Optional<CustomerVehicle> findByOwnerPhone(String ownerPhone);
}