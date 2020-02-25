package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;


@Controller
public class ReportController {

    @GetMapping("/")
    public String index(Model model, @RequestParam(value="name", required=false, defaultValue="Hello World!") String name) {
        ArrayList<Report> reports = new ArrayList<Report>();

        for (int i = 0; i < 10; i++) {
            Report report = new Report(LocalDateTime.now());
            reports.add(report);
        }

        model.addAttribute("name", name);
        model.addAttribute("reports", reports);

        return "index";
    }
}
