package com.doommap.web.module.report.service;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void create() {
        Report report = new Report(LocalDateTime.now());
        reportRepository.save(report);
    }
}
