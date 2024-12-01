package com.driveaze.driveaze.controller;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.service.AnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping(path = "/update")
    public ResponseEntity<ResponseDTO> updateAnnouncement(@RequestBody AnnouncementDTO announcementDTO){
        ResponseDTO responseDTO = announcementService.updateAnnouncement(announcementDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path= "/get-by-id", params = "id")
    public ResponseEntity<ResponseDTO> getAnnouncementById(@RequestParam(value = "id") int announcementId){
        ResponseDTO responseDTO = announcementService.getAnnouncementById(announcementId);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping(path = "get-all-announcements")
    public ResponseEntity<ResponseDTO> getAllAnnouncements(){
        ResponseDTO responseDTO = announcementService.getAllAnnouncements();
        return ResponseEntity.ok(responseDTO);
    }
}

