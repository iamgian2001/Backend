package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.ServiceBooking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ServiceBookingRepo extends JpaRepository<ServiceBooking,Integer> {
}
