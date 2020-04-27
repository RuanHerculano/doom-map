package com.doommap.module.report.bean;

public class CrimeGUIBean {
    private String crimeID;
    private String timeOfEvent;
    private String cep;

    public CrimeGUIBean() {}

    public CrimeGUIBean(String crimeID, String timeOfEvent, String cep) {
        this.crimeID = crimeID;
        this.timeOfEvent = timeOfEvent;
        this.cep = cep;
    }

    public void setCrimeID(String crimeID) {
        this.crimeID = crimeID;
    }

    public String getCrimeID() {
        return this.crimeID;
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
