package com.doommap.web.module.report.service;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    public void create() {
        Report report = new Report();
        reportRepository.save(report);
    }
}
