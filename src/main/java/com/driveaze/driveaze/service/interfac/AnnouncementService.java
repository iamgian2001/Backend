package com.driveaze.driveaze.service.interfac;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Announcement;
import com.driveaze.driveaze.entity.JobRegistry;
import org.springframework.data.domain.Page;

import java.util.List;

public interface  AnnouncementService {
    ResponseDTO addAnnouncement(AnnouncementDTO announcementDTO);

    ResponseDTO updateAnnouncement(AnnouncementDTO announcementDTO);

    Page<Announcement> findAnnsWithPaginationAndSorting(int offset);

    ResponseDTO getAnnouncementById(int announcementId);

    ResponseDTO getAllAnnouncements();

    ResponseDTO deleteAnnouncement(Integer announcementId);

    ResponseDTO getStaffAnnouncements();

    ResponseDTO getCustomerAnnouncements();
}
