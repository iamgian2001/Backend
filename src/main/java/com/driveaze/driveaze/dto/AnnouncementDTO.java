package com.driveaze.driveaze.dto;

import jakarta.persistence.Column;

import java.util.Date;

public class AnnouncementDTO {
    private int announcementId;
    private String title;
    private Date date;
    private String content;
    private String recivers;

    public AnnouncementDTO() {
    }

    public AnnouncementDTO(int announcementId, String title, Date date, String content, String recivers) {
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
        return "AnnouncementDTO{" +
                "announcementId=" + announcementId +
                ", title='" + title + '\'' +
                ", date=" + date +
                ", content='" + content + '\'' +
                ", recivers='" + recivers + '\'' +
                '}';
    }
}
