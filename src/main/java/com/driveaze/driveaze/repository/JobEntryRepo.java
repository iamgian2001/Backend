package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.JobEntry;
import java.util.List;
import com.driveaze.driveaze.entity.JobRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface JobEntryRepo extends JpaRepository<JobEntry, Integer> {
    List<JobEntry> findByJobRegistry_JobId(int jobId);

}
