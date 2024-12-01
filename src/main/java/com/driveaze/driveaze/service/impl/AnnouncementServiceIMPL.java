package com.driveaze.driveaze.service.impl;

import com.driveaze.driveaze.dto.AnnouncementDTO;
import com.driveaze.driveaze.dto.ResponseDTO;
import com.driveaze.driveaze.entity.Announcement;
import com.driveaze.driveaze.entity.ManHourPricing;
import com.driveaze.driveaze.exception.OurException;
import com.driveaze.driveaze.repository.AnnouncementRepo;
import com.driveaze.driveaze.service.AnnouncementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseDTO updateAnnouncement(AnnouncementDTO announcementDTO) {
        ResponseDTO response = new ResponseDTO();

        if(announcementRepo.existsById(announcementDTO.getAnnouncementId())){
            Announcement announcement = announcementRepo.getById(announcementDTO.getAnnouncementId());

            announcement.setTitle(announcementDTO.getTitle());
            announcement.setDate(announcementDTO.getDate());
            announcement.setContent(announcementDTO.getContent());
            announcement.setRecivers(announcementDTO.getRecivers());

            announcementRepo.save(announcement);
            response.setStatusCode(200);
            response.setMessage("Successfully updated announcement");
        }else{
            response.setStatusCode(400);
            response.setMessage("no announcement found");
            System.out.println("no announcement found");
        }
        return response;
    }

//    @Override
//    public AnnouncementDTO getAnnouncementById(int announcementId) {
//        Announcement announcement = announcementRepo.getById(announcementId);
//        if(announcement!=null){
//            AnnouncementDTO announcementDTO = new AnnouncementDTO(
//                    announcement.getAnnouncementId(),
//                    announcement.getTitle(),
//                    announcement.getDate(),
//                    announcement.getContent(),
//                    announcement.getRecivers()
//            );
//            return  announcementDTO;
//        }else {
//            return null;
//        }
//    }

    @Override
    public ResponseDTO getAnnouncementById(int announcementId){
        ResponseDTO response = new ResponseDTO();

        Announcement announcement = announcementRepo.getById(announcementId);
        AnnouncementDTO announcementDTO = modelMapper.map(announcement,AnnouncementDTO.class);
        return response;
    }

    @Override
    public ResponseDTO getAllAnnouncements() {
        ResponseDTO response = new ResponseDTO();

        try {
            List<Announcement>getAnnouncements = announcementRepo.findAll();
            if (!getAnnouncements.isEmpty()){
                response.setAnnouncementList(getAnnouncements);
                response.setStatusCode(200);
                response.setMessage("Successful");
            } else {
                throw new OurException("No Man Hour Prices Found");
            }
        } catch (OurException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving Man Hour Prices: " + e.getMessage());
        }

        return response;

//        List<Announcement>getAnnouncements = announcementRepo.findAll();
//        List<AnnouncementDTO>announcementDTOList =new ArrayList<>();
//        for(Announcement announcement : getAnnouncements){
//            AnnouncementDTO announcementDTO = new AnnouncementDTO(
//                    announcement.getAnnouncementId(),
//                    announcement.getTitle(),
//                    announcement.getDate(),
//                    announcement.getContent(),
//                    announcement.getRecivers()
//            );
//            announcementDTOList.add(announcementDTO);
//        }
//        return announcementDTOList;
//    }
}
@Override
public ResponseDTO getAllManHourPricings() {

    ResponseDTO response = new ResponseDTO();

    try {
        List<ManHourPricing> manHourPricings = manHourPricingRepo.findAll();
        if (!manHourPricings.isEmpty()){
            response.setManHourPricingList(manHourPricings);
            response.setStatusCode(200);
            response.setMessage("Successful");
        } else {
            throw new OurException("No Man Hour Prices Found");
        }
    } catch (OurException e) {
        response.setStatusCode(404);
        response.setMessage(e.getMessage());
    } catch (Exception e) {
        response.setStatusCode(500);
        response.setMessage("Error occurred while retrieving Man Hour Prices: " + e.getMessage());
    }

    return response;
}
