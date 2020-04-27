package com.doommap.module.report.service;

import com.doommap.module.report.bean.CrimeGUIBean;
import com.doommap.module.report.entity.Address;
import com.doommap.module.report.entity.Crime;
import com.doommap.module.report.entity.Report;
import com.doommap.module.report.entity.ReportCrime;
import com.doommap.module.report.repository.AddressRepository;
import com.doommap.module.report.repository.CrimeRepository;
import com.doommap.module.report.repository.ReportCrimeRepository;
import com.doommap.module.report.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public Report findById(long reportID) {
        return reportRepository.findById(reportID).orElse(null);
    }

    public List<ReportCrime> create(CrimeGUIBean[] crimes) {
        Report report = new Report();
        Report createdReport = reportRepository.save(report);

        List<ReportCrime> reportCrimes = new ArrayList<>();
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

            reportCrimes.add(reportCrimeRepository.save(reportCrime));
        }

        return reportCrimes;
    }

    public List<ReportCrime> update(long reportId, CrimeGUIBean[] crimes) {
        Report report = reportRepository.findById(reportId).orElse(null);
        reportCrimeRepository.deleteAllReportCrimesByReportId(report);

        List<ReportCrime> reportCrimes = new ArrayList<>();
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

            reportCrimes.add(reportCrimeRepository.save(reportCrime));
        }

        return reportCrimes;
    }

    public void destroy(long reportId) {
        Report report = reportRepository.findById(reportId).orElse(null);
        reportCrimeRepository.deleteAllReportCrimesByReportId(report);
        reportRepository.deleteById(reportId);
    }
}
