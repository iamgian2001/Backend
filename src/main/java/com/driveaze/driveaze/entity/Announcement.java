package com.driveaze.driveaze.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@Entity
@Table(name = "announcement")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Announcement {

    @Id
    @Column(name = "Announcement_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int announcementId;

    @Column(name = "title" , length =100)
    private String title;

    @Column(name = "date" , length =100)
    private LocalDate date;

    @Column(name = "time" , length =100)
    private LocalTime time;

    @Column(name = "content" , length =100)
    private String content;

    @Column(name = "recivers" , length =100)
    private String recivers;

}
