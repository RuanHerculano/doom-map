package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.service.ReportService;
import com.doommap.web.module.report.bean.CrimeGUIBean;

import com.google.gson.Gson;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.http.MediaType;
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

    @PostMapping(value = "/report/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createReport(Model model, @RequestBody String json) {
        Gson gson = new Gson();

        CrimeGUIBean[] crimes = gson.fromJson(json, CrimeGUIBean[].class);

        reportService.create(crimes);

        return "report/success";
    }
}
