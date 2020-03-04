package com.doommap.web.module.report.service;

import com.doommap.web.module.report.bean.CrimeGUIBean;
import com.doommap.web.module.report.entity.Address;
import com.doommap.web.module.report.entity.Crime;
import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.entity.ReportCrime;
import com.doommap.web.module.report.repository.AddressRepository;
import com.doommap.web.module.report.repository.CrimeRepository;
import com.doommap.web.module.report.repository.ReportCrimeRepository;
import com.doommap.web.module.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ReportCrimeRepository reportCrimeRepository;

    @Autowired
    private CrimeRepository crimeRepository;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public void create(CrimeGUIBean[] crimes) {
        Report report = new Report();
        Report createdReport = reportRepository.save(report);

        for(CrimeGUIBean crimeGUIBean : crimes) {
            Address address = new Address(crimeGUIBean.getCep());
            Address createdAddress = addressRepository.save(address);

            Crime crime = crimeRepository.findById(Long.parseLong(crimeGUIBean.getCrimeID())).orElse(null);

            ReportCrime reportCrime = new ReportCrime(
                LocalDateTime.parse(crimeGUIBean.getTimeOfEvent(), formatter),
                createdAddress,
                crime,
                createdReport
            );

            reportCrimeRepository.save(reportCrime);
        }
    }

    public Report findById(long reportID) {
        return reportRepository.findById(reportID).orElse(null);
    }

    public void update(long reportId, CrimeGUIBean[] crimes) {
        Report report = reportRepository.findById(reportId).orElse(null);
        reportCrimeRepository.deleteAllReportCrimesByReportId(report);

        for(CrimeGUIBean crimeGUIBean : crimes) {
            Address address = new Address(crimeGUIBean.getCep());
            Address createdAddress = addressRepository.save(address);

            Crime crime = crimeRepository.findById(Long.parseLong(crimeGUIBean.getCrimeID())).orElse(null);

            ReportCrime reportCrime = new ReportCrime(
                    LocalDateTime.parse(crimeGUIBean.getTimeOfEvent(), formatter),
                    createdAddress,
                    crime,
                    report
            );

            reportCrimeRepository.save(reportCrime);
        }
    }
}
