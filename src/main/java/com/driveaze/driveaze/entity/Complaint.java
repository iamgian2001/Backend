package com.driveaze.driveaze.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "complaint")
public class Complaint {

    @Id
    @Column(name = "complaint_id",length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int complaintId;

    @Column(name = "description", length =500, nullable = false)
    private String description;

    @Column(name = "status", length = 1)
    private int status;
}
