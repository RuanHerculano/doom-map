package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.service.ReportService;
import com.doommap.web.module.report.bean.CrimeGUIBean;

import com.google.gson.Gson;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping({"/", "/report"})
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="Hello World!") String name) {
        List<Report> reports = reportService.findAll();

        model.addAttribute("reports", reports);

        return "report/index";
    }

    @GetMapping("/report/new")
    public String newReport() {
        return "report/new";
    }

    @PostMapping(value = "/report/create")
    public String createReport(Model model, @RequestBody String json) {
        Gson gson = new Gson();

        CrimeGUIBean[] crimes = gson.fromJson(json, CrimeGUIBean[].class);

        reportService.create(crimes);

        return "report/success";
    }

    @GetMapping("/report/edit/{reportId}")
    public String edit(Model model, @PathVariable(value="reportId") long reportId) {
        Report report = reportService.findById(reportId);

        model.addAttribute("report", report);

        return "report/edit2";
    }

    @PostMapping("/report/update/{reportId}")
    public String update(@PathVariable(value="reportId") long reportId, @RequestBody String json) {
        Gson gson = new Gson();

        CrimeGUIBean[] crimes = gson.fromJson(json, CrimeGUIBean[].class);

        reportService.update(reportId, crimes);

        return "report/success";
    }

    @PostMapping("/report/delete/{reportId}")
    public String destroy(@PathVariable(value="reportId") long reportId) {
        reportService.destroy(reportId);

        return "report/success";
    }
}
