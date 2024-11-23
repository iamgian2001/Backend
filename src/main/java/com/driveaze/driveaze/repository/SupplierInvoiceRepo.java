package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.entity.SupplierInvoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface SupplierInvoiceRepo extends JpaRepository<SupplierInvoice, Integer> {

}
