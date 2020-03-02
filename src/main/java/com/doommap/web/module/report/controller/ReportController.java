package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.service.ReportService;
import com.doommap.web.module.report.bean.CrimeGUIBean;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
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
    public String createReport(Model model, @RequestBody String json) throws IOException {
        Gson gson = new Gson();

        CrimeGUIBean[] userArray = gson.fromJson(json, CrimeGUIBean[].class);

        for(CrimeGUIBean crime : userArray) {
            System.out.println("asdasdasdasdasdasdasdass");
            System.out.println(crime.getCrime());
        }

        List<Report> reports = reportService.findAll();
        model.addAttribute("reports", reports);

        return "report/index";
    }

    private String keyNameOfCrimeObject(String str) {
        String[] arrOfStr = str.split("\\]\\[", 2);
        String keyName = arrOfStr[1].replace("]", "");
        return keyName;
    }

    private int indexOfCrimeObject(String str) {
        String[] arrOfStr = str.split("\\]\\[", 2);
        int index = Integer.parseInt(arrOfStr[0].replace("crimes[", ""));
        return index;
    }
}
