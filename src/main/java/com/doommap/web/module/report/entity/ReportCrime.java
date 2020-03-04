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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Address address;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "crime_id", referencedColumnName = "id")
    private Crime crime;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne
    @JoinColumn(name="report_id")
    private Report report;

    public ReportCrime() {}

    public ReportCrime(LocalDateTime timeOfEvent, Address address, Crime crime, Report report) {
        this.timeOfEvent = timeOfEvent;
        this.address = address;
        this.crime = crime;
        this.report = report;
    }

    public LocalDateTime getTimeOfEvent() {
        return this.timeOfEvent;
    }

    public Address getAddress() {
        return this.address;
    }

    public Crime getCrime() {
        return this.crime;
    }
}
