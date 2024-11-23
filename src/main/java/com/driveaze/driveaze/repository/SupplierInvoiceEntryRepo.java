package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.SupplierInvoiceEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface SupplierInvoiceEntryRepo extends JpaRepository<SupplierInvoiceEntry, Integer> {
    List<SupplierInvoiceEntry> findBySupplierInvoice_InvoiceId(Integer invoiceId);
}
