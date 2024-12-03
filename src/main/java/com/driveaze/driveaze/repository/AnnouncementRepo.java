package com.driveaze.driveaze.repository;

import com.driveaze.driveaze.entity.Announcement;
import com.driveaze.driveaze.entity.OurUsers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableJpaRepositories
@Repository
public interface AnnouncementRepo extends JpaRepository<Announcement, Integer> {
//    @Query(" SELECT a FROM Announcement a" +
//            " WHERE a.recivers = 'ALL' " +
//            " OR :role")
//    List<Announcement> findAnnouncementsForRole(String role);

    List<Announcement> findByReciversOrRecivers(String all,String role);

//    List<Announcement> findTop3ByOrderByAnnouncementIdDesc();

    @Query("SELECT a FROM Announcement a WHERE a.recivers IN ('ALL', 'CUSTOMER') ORDER BY a.announcementId DESC")
    List<Announcement> findCustomerDashAnnouncements();

    @Query("SELECT a FROM Announcement a WHERE a.recivers IN ('ALL', 'STAFF') ORDER BY a.announcementId DESC")
    List<Announcement> findStaffAnnouncements();

    @Query("SELECT a FROM Announcement a " +
            "WHERE a.recivers = 'ALL' " +
            "OR a.recivers = :role")
    List<Announcement> findAnnouncementsForRole(String role);
}
