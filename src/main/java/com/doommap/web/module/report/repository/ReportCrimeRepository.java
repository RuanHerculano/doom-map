package com.doommap.web.module.report.repository;

import com.doommap.web.module.report.entity.ReportCrime;
import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportCrimeRepository extends JpaRepository<ReportCrime, Long> {
}