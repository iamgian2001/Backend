package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.CustomerVehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface BillRepo extends JpaRepository<Bill, Integer >{

    Optional<Bill> findByJobIdAndBillStatusIn(int jobId, List<Integer> ongoingStatuses);
}
