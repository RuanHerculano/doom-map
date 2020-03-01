package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.service.ReportService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReportController {

    @Autowired
    private ReportService reportService;

    @GetMapping("/")
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="Hello World!") String name) {
        List<Report> reports = reportService.findAll();
        
        model.addAttribute("reports", reports);

        return "report/index";
    }
}
