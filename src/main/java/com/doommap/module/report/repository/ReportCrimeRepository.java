package com.doommap.module.report.repository;

import com.doommap.module.report.entity.Report;
import com.doommap.module.report.entity.ReportCrime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ReportCrimeRepository extends JpaRepository<ReportCrime, Long> {

    @Transactional
    @Modifying
    @Query("DELETE FROM ReportCrime rc WHERE rc.report.id =:#{#report.id}")
    void deleteAllReportCrimesByReportId(@Param("report") Report report);
}