package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface JobRegistryRepo extends JpaRepository<OurUsers, Long> {

    boolean existsByEmail(String name);
    Optional<OurUsers> findByEmail(String email);
}
