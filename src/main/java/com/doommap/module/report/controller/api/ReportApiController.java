package com.doommap.module.report.controller.api;

import com.doommap.module.report.entity.Report;
import com.doommap.module.report.entity.ReportCrime;
import com.doommap.module.report.service.ReportService;
import com.doommap.module.report.bean.CrimeGUIBean;

import com.google.gson.Gson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/api/reports")
public class ReportApiController {

    @Autowired
    private ReportService reportService;

    @GetMapping
    public List<Report> index() {
        return reportService.findAll();
    }

    @GetMapping("/{reportId}")
    public Report show(@PathVariable(value="reportId") long reportId) {
        return reportService.findById(reportId);
    }

    @PostMapping
    public List<ReportCrime> createReport(@RequestBody String json) {
        Gson gson = new Gson();

        CrimeGUIBean[] crimes = gson.fromJson(json, CrimeGUIBean[].class);

        return reportService.create(crimes);
    }

    @PutMapping("/{reportId}")
    public List<ReportCrime> update(@PathVariable(value="reportId") long reportId, @RequestBody String json) {
        Gson gson = new Gson();

        CrimeGUIBean[] crimes = gson.fromJson(json, CrimeGUIBean[].class);

        return reportService.update(reportId, crimes);
    }

    @DeleteMapping("/{reportId}")
    public void destroy(@PathVariable(value="reportId") long reportId) {
        reportService.destroy(reportId);
    }
}
