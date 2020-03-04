package com.doommap.web.module.report.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "report")
public class Report {

	@Id
	@GeneratedValue
	@Column(name = "id")
	long id;

	@Column(name = "uuid")
	private String uuid;

	@Column(name = "created_at")
	@CreationTimestamp
	private LocalDateTime createdAt;

	@Column(name = "updated_at")
	@UpdateTimestamp
	private LocalDateTime updatedAt;

	@OneToMany(mappedBy = "report", cascade = CascadeType.ALL)
	private Set<ReportCrime> reportCrimes;

	public Report() {
		UUID uuid = UUID.randomUUID();
		this.uuid = uuid.toString();
	}

	public Set<ReportCrime> getReportCrimes() {
		return reportCrimes;
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}
}
