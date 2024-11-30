package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.BillEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface BillEntryRepo extends JpaRepository<BillEntry, Integer > {

    List<BillEntry> findAllByBill(Bill bill);
}
