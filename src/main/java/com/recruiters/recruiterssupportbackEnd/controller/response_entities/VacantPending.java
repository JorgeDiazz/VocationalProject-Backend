package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.Date;

public class VacantPending {
   
    private int id;
    private String jobPositionName;
    private int recruitersNumber;
    private Date startDate;
    private int placesNumber;
    private int idJobPosition; // Jhoan 20/10/2019

    public int getIdJobPosition() {
        return idJobPosition;
    }

    public void setIdJobPosition(int idJobPosition) {
        this.idJobPosition = idJobPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getJobPositionName() {
        return jobPositionName;
    }

    public void setJobPositionName(String jobPositionName) {
        this.jobPositionName = jobPositionName;
    }

    public int getRecruitersNumber() {
        return recruitersNumber;
    }

    public void setRecruitersNumber(int recruitersNumber) {
        this.recruitersNumber = recruitersNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }    
}