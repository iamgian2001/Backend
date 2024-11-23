package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface SupplierRepo extends JpaRepository<Supplier, Integer> {

    boolean existsBySupplierEmail(String supplierEmail);
}
