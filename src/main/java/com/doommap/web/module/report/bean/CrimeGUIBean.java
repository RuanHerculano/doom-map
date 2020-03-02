package com.doommap.web.module.report.bean;

public class CrimeGUIBean {
    private String crimeCode;
    private String timeOfEvent;
    private String cep;

    public CrimeGUIBean() {}

    public CrimeGUIBean(String crimeCode, String timeOfEvent, String cep) {
        this.crimeCode = crimeCode;
        this.timeOfEvent = timeOfEvent;
        this.cep = cep;
    }

    public void setCrime(String crimeCode) {
        this.crimeCode = crimeCode;
    }

    public String getCrime() {
        return this.crimeCode;
    }

    public void setTimeOfEvent(String timeOfEvent) {
        this.timeOfEvent = timeOfEvent;
    }

    public String getTimeOfEvent() {
        return this.timeOfEvent;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getCep() {
        return this.cep;
    }
}
