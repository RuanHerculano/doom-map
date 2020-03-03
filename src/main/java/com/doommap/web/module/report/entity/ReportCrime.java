package com.doommap.web.module.report.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "report_crime")
public class ReportCrime {

    @Id
    @GeneratedValue
    @Column(name = "id")
    long id;

    @Column(name = "time_of_event")
    LocalDateTime timeOfEvent;

    @Column(name = "address_id")
    long addressID;

    @Column(name = "crime_id")
    long crimeID;

    @Column(name = "report_id")
    long reportID;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public ReportCrime(LocalDateTime timeOfEvent, long addressID, long crimeID, long reportID) {
        this.timeOfEvent = timeOfEvent;
        this.addressID = addressID;
        this.crimeID = crimeID;
        this.reportID = reportID;
    }
}
