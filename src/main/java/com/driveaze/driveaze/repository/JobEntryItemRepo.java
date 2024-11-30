package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.JobEntryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobEntryItemRepo extends JpaRepository<JobEntryItem, Integer> {
}
