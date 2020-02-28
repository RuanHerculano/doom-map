package com.doommap.web.module.report.entity;

import com.doommap.web.module.report.entity.Report;
import java.time.LocalDateTime;
import javax.persistence.*;

@Entity
@Table(name="address")
public class Address {

    @Id
    @GeneratedValue
    @Column(name="id")
    private long id;

    @OneToOne(mappedBy = "address")
    private Report report;

    @Column(name="created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name="updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    public Address() {}
}