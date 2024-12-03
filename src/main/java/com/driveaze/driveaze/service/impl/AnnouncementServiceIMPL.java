package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.BookingDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.*;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.AnnouncementRepo;
import com.driveaze.driveaze.service.interfac.AnnouncementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class AnnouncementServiceIMPL implements AnnouncementService {

    @Autowired
    private AnnouncementRepo announcementRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ResponseDTO addAnnouncement(AnnouncementDTO announcementDTO) {
        ResponseDTO response = new ResponseDTO();
        Announcement announcement = new Announcement(
                announcementDTO.getAnnouncementId(),
                announcementDTO.getTitle(),
                announcementDTO.getDate(),
                announcementDTO.getTime(),
                announcementDTO.getContent(),
                announcementDTO.getRecivers()
        );

        if(!announcementRepo.existsById(announcement.getAnnouncementId())){
            announcementRepo.save(announcement);
            response.setStatusCode(200);
            response.setMessage("Successfully added announcement");
        }else{
            System.out.println("Announcement id already exists");
            response.setStatusCode(400);
            response.setMessage("Announcement id already exists");
        }
        return response;
    }
    @Override
    public ResponseDTO updateAnnouncement(AnnouncementDTO announcementDTO){
        ResponseDTO response = new ResponseDTO();

        try {
            if(announcementRepo.existsById(announcementDTO.getAnnouncementId())){
                Announcement announcement = announcementRepo.getById(announcementDTO.getAnnouncementId());
                announcement.setAnnouncementId(announcementDTO.getAnnouncementId());
                announcement.setContent(announcementDTO.getContent());
                announcement.setTitle(announcementDTO.getTitle());
                announcement.setRecivers(announcementDTO.getRecivers());
                announcementRepo.save(announcement);
                response.setStatusCode(200);
                response.setMessage("Successfully updated announcement");
            }else{
                throw new OurException("No announcement Found");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occured while updating announcement: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAnnouncementById(int announcementId){
        ResponseDTO response = new ResponseDTO();

        try {
            Announcement announcement = announcementRepo.findById(announcementId)
                    .orElseThrow(() -> new OurException("AnnouncementId not found"));

            response.setAnnouncement(announcement);
            response.setStatusCode(200);
            response.setMessage("Successfully Found Announcement");
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while Announcement: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getAllAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement> getAnnouncements = announcementRepo.findAll();
            if (!getAnnouncements.isEmpty()) {
                response.setAnnouncementList(getAnnouncements);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Announcement Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Announcement: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO deleteAnnouncement(Integer announcementId) {

        ResponseDTO response = new ResponseDTO();

        try {
            if(announcementRepo.existsById(announcementId)){
                announcementRepo.deleteById(announcementId);
                response.setStatusCode(200);
                response.setMessage("Successfully deleted announcement ");
            }else{
                System.out.println("announcement found under this id");
                throw new OurException("announcement found under this id.");
            }
        }catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        }catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while deleting announcement: " + e.getMessage());
        }
        return response;
    }
    @Override
    public ResponseDTO getStaffAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement> getAnnouncements = announcementRepo.findByReciversOrRecivers("ALL", "STAFF");
            if (!getAnnouncements.isEmpty()) {
                response.setAnnouncementList(getAnnouncements);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Announcement Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Announcement: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getCustomerAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement> getAnnouncements = announcementRepo.findByReciversOrRecivers("ALL", "CUSTOMER");
            if (!getAnnouncements.isEmpty()) {
                response.setAnnouncementList(getAnnouncements);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Announcement Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Announcement: " + e.getMessage());
        }
        return response;
    }
    @Override
    public Page<Announcement> findAnnsWithPaginationAndSorting(int offset){
        return announcementRepo.findAll(PageRequest.of(offset, 9).withSort(Sort.by(Sort.Order.desc("date"), Sort.Order.desc("time"))));
    }

    @Override
    public ResponseDTO getDashCustomerAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement> announcements = announcementRepo.findCustomerDashAnnouncements();
            if (!announcements.isEmpty()) {
                response.setAnnouncementList(announcements);
                response.setStatusCode(200);
                response.setMessage("Successfully retrieved the last announcements.");
            } else {
                throw new OurException("No announcements found.");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving announcements: " + e.getMessage());
        }
        return response;
    }

    @Override
    public ResponseDTO getDashStaffAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement> announcements = announcementRepo.findStaffAnnouncements();
            if (!announcements.isEmpty()) {
                response.setAnnouncementList(announcements);
                response.setStatusCode(200);
                response.setMessage("Successfully retrieved the three announcements.");
            } else {
                throw new OurException("No announcements found.");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving announcements: " + e.getMessage());
        }
        return response;
    }
}
