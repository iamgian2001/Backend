package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.dto.ComplaintDTO;
import com.driveaze.driveaze.entity.Complaint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface ComplaintRepo extends JpaRepository<Complaint,Integer> {

}
