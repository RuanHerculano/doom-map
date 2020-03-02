package com.doommap.web.module.report.bean;

public class CrimeGUIBean {
    private String crime;
    private String timeOfEvent;
    private String cep;

    public CrimeGUIBean(String crime, String timeOfEvent, String cep) {
        this.crime = crime;
        this.timeOfEvent = timeOfEvent;
        this.cep = cep;
    }

    public String getCrime() {
        return this.crime;
    }

    public String getTimeOfEvent() {
        return this.timeOfEvent;
    }

    public String getCep() {
        return this.cep;
    }
}
