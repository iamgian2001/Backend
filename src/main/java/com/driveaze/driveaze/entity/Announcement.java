package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "announcement")
public class Announcement {

    @Id
    @Column(name = "Announcement_id", length =45)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int announcementId;

    @Column(name = "title" , length =100)
    private String title;

    @Column(name = "date" , length =100)
    private Date date;

    @Column(name = "content" , length =100)
    private String content;

    @Column(name = "recivers" , length =100)
    private String recivers;

    public Announcement(){
    }

    public Announcement(int announcementId, String title, Date date, String content, String recivers) {
        this.announcementId = announcementId;
        this.title = title;
        this.date = date;
        this.content = content;
        this.recivers = recivers;
    }

    public int getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(int announcementId) {
        this.announcementId = announcementId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRecivers() {
        return recivers;
    }

    public void setRecivers(String recivers) {
        this.recivers = recivers;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "announcementId=" + announcementId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", recivers='" + recivers + '\'' +
                '}';
    }
}
