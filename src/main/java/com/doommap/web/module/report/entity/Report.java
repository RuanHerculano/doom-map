package com.doommap.web.module.report.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

	@Id
	@GeneratedValue
	@Column(name = "id")
	long id = 0;

	@Column(name = "uuid")
	private String uuid = "";

	@Column(name = "created_at")
	private LocalDateTime createdAt = LocalDateTime.now();

	@Column(name = "updated_at")
	private LocalDateTime updatedAt = LocalDateTime.now();

	public Report() {
	}

	public String getUuid() {
		return this.uuid;
	}
}
