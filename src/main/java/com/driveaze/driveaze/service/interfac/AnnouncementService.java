package com.driveaze.driveaze.service;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.ResponseDTO;

import java.util.List;

public interface  AnnouncementService {
    ResponseDTO addAnnouncement(AnnouncementDTO announcementDTO);

    ResponseDTO updateAnnouncement(AnnouncementDTO announcementDTO);

    ResponseDTO getAnnouncementById(int announcementId);

    ResponseDTO getAllAnnouncements();
}
