package com.doommap.web.module.report.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Report {
    private LocalDateTime date;

    public Report(LocalDateTime date) {
        this.date = date;
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return this.date.format(formatter);
    }
}
