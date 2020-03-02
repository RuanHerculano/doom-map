package com.doommap.web.module.report.controller;

import com.doommap.web.module.report.entity.Report;
import com.doommap.web.module.report.service.ReportService;

import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;

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

        return "/report/index";
    }

    @GetMapping("/report/new")
    public String newReport() {
        return "report/new";
    }

    @PostMapping(value = "/report/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createReport(Model model, @RequestParam Map<String, String> paramMap) {
        List<Map<String , String>> crimes = new ArrayList<Map<String,String>>();
        Map<String,String> mapCrime = new HashMap<String, String>();
        ArrayList<Integer> crimeInstancesControl = new ArrayList<Integer>();

        int index = 0;
        for (Map.Entry<String,String> crime : paramMap.entrySet()) {
            String keyName = this.keyNameOfCrimeObject(crime.getKey());
            int crimeIndex = this.indexOfCrimeObject(crime.getKey());

            if (!crimeInstancesControl.contains(crimeIndex)) {
                crimeInstancesControl.add(crimeIndex);
                mapCrime = new HashMap<String, String>();
            }

            mapCrime.put(keyName, crime.getValue());
            crimes.add(index, mapCrime);
            index++;
        }

        for (Map<String,String> map : crimes) {
            System.out.println("______________________________");
            System.out.println(map);
            System.out.println("______________________________");
        }

        List<Report> reports = reportService.findAll();
        model.addAttribute("reports", reports);

        return "/report/index";
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
