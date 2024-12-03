package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Bill;
import com.driveaze.driveaze.entity.CustomerVehicle;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@EnableJpaRepositories
@Repository
public interface BillRepo extends JpaRepository<Bill, Integer >{

    Optional<Bill> findByJobRegistry_JobId(Integer jobId);

    List<Bill> findByBillStatus(Integer billStatus);


    @Query("SELECT b FROM Bill b WHERE b.billStatus IN :statuses")
    Page<Bill> findByBillStatusIn(@Param("statuses") List<Integer> statuses, Pageable pageable);

    Bill getBillByBillId(Integer billId);

    @Query("SELECT b FROM Bill b " +
            "JOIN JobRegistry j ON b.jobRegistry.jobId = j.jobId " +
            "JOIN CustomerVehicle cv ON j.vehicleId = cv.vehicleId " +
            "WHERE cv.ownerPhone = :phoneNo " +
            "AND b.billStatus IN :statuses")
    Page<Bill> findBillsByCustomerPhoneAndStatuses(
            @Param("phoneNo") String phoneNo,
            @Param("statuses") List<Integer> statuses,
            Pageable pageable
    );
}
