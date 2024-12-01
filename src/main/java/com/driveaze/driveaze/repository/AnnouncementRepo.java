package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@EnableJpaRepositories
@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, Integer> {
}
