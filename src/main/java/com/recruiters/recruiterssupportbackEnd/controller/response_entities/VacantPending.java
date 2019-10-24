package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

public class VacantPending {
   
    private int id;
    private String jobPositionName;
    private int recruitersNumber;
    private Long startDate;
    private int placesNumber;

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

    public Long getStartDate() {
        return startDate;
    }

    public void setStartDate(Long startDate) {
        this.startDate = startDate;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }    
}