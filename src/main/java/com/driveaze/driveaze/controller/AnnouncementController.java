package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Announcement;
import com.driveaze.driveaze.entity.JobRegistry;
import com.driveaze.driveaze.service.interfac.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/announcement")
@CrossOrigin
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @PostMapping(path = "/save")
    public ResponseEntity<ResponseDTO> saveAnnouncement(@RequestBody AnnouncementDTO announcementDTO){
        return ResponseEntity.ok(announcementService.addAnnouncement(announcementDTO));
    }

    @PutMapping(path = "/update/{announcementId}")
    public ResponseEntity<ResponseDTO> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO){
        ResponseDTO responseDTO = announcementService.updateAnnouncement(announcementDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path= "/get-by-id/{announcementId}", params = "id")
    public ResponseEntity<ResponseDTO> getAnnouncementById(@RequestParam(value = "id") int announcementId){
        ResponseDTO responseDTO = announcementService.getAnnouncementById(announcementId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "get-all-announcements")
    public ResponseEntity<ResponseDTO> getAllAnnouncements(){
        ResponseDTO responseDTO = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "get-staff-announcements")
    public ResponseEntity<ResponseDTO> getStaffAnnouncements(){
        ResponseDTO responseDTO = announcementService.getStaffAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "get-customer-announcements")
    public ResponseEntity<ResponseDTO> getCustomerAnnouncements(){
        ResponseDTO responseDTO = announcementService.getCustomerAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/delete/{announcementId}")
    public ResponseEntity<ResponseDTO> deleteAnnouncement(@PathVariable Integer announcementId) {
        return ResponseEntity.ok(announcementService.deleteAnnouncement(announcementId));
    }

    @GetMapping("/paginationAndSort/{offset}")
    public Page<Announcement> findAnnsWithPaginationAndSorting(@PathVariable int offset) {
        return announcementService.findAnnsWithPaginationAndSorting(offset);
    }

    @GetMapping(path = "get-dash-customer-announcements")
    public ResponseEntity<ResponseDTO> getDashCustomerAnnouncements(){
        ResponseDTO responseDTO = announcementService.getDashCustomerAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "get-dash-staff-announcements")
    public ResponseEntity<ResponseDTO> getDashStaffAnnouncements(){
        ResponseDTO responseDTO = announcementService.getDashStaffAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }
}

