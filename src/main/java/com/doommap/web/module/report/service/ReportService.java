package com.doommap.web.module.report.service;

import com.doommap.web.module.report.bean.CrimeGUIBean;
import com.doommap.web.module.report.entity.Address;
import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.entity.ReportCrime;
import com.doommap.web.module.report.repository.AddressRepository;
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

    public List<Report> findAll() {
        return reportRepository.findAll();
    }

    public void create(CrimeGUIBean[] crimes) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        Report report = new Report();
        Report createdReport = reportRepository.save(report);

        for(CrimeGUIBean crime : crimes) {
            Address address = new Address(crime.getCep());
            Address createdAddress = addressRepository.save(address);

            ReportCrime reportCrime = new ReportCrime(
                LocalDateTime.parse(crime.getTimeOfEvent(), formatter),
                createdAddress.getId(),
                Long.parseLong(crime.getCrimeID()),
                createdReport.getId()
            );

            reportCrimeRepository.save(reportCrime);
        }
    }
}
