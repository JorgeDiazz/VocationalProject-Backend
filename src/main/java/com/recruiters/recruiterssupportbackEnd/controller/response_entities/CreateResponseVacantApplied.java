/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author jhanu
 */
@Entity
public class CreateResponseVacantApplied {
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "name_job")
    private String nameJob;
    @Column(name = "salary_min")
    private Double salaryMin;
    @Column(name = "salary_max")
    private Double salaryMax;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "places_number")
    private int placesNumber;
    @Column(name = "state")
    private int state; //(0: Si en la tabla no hay id_rv, 1:Si hay id_rv,2: Si el reclutador lo rechaza)

    public CreateResponseVacantApplied() {
    }

    public CreateResponseVacantApplied(int id, String nameJob, Double salaryMin, Double salaryMax, Date startDate, int placesNumber, int state) {
        this.id = id;
        this.nameJob = nameJob;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.startDate = startDate;
        this.placesNumber = placesNumber;
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameJob() {
        return nameJob;
    }

    public void setNameJob(String nameJob) {
        this.nameJob = nameJob;
    }

    public Double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Double salaryMax) {
        this.salaryMax = salaryMax;
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

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

}
