package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnnouncementDTO {
    private int announcementId;
    private String title;
    private LocalDate date;
    private LocalTime time;
    private String content;
    private String recivers;


}
