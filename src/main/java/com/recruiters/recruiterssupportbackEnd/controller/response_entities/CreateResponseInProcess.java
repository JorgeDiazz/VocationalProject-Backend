/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jhanu
 */
public class CreateResponseInProcess {
    
private String area;
private Double salaryMin;
private Double salaryMax;
private int placesNumber;
private Date startDate; 
private List<String> careers; 
private List<String> processes;
private List<String> hardSkills; 
private List<String> softSkills;
private List<String> postulants;

    public CreateResponseInProcess() {
    }

    public CreateResponseInProcess(String area, Double salaryMin, Double salaryMax, int placesNumber, Date startDate, List<String> careers, List<String> processes, List<String> hardSkills, List<String> softSkills, List<String> postulants) {
        this.area = area;
        this.salaryMin = salaryMin;
        this.salaryMax = salaryMax;
        this.placesNumber = placesNumber;
        this.startDate = startDate;
        this.careers = careers;
        this.processes = processes;
        this.hardSkills = hardSkills;
        this.softSkills = softSkills;
        this.postulants = postulants;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public List<String> getCareers() {
        return careers;
    }

    public void setCareers(List<String> careers) {
        this.careers = careers;
    }

    public List<String> getProcesses() {
        return processes;
    }

    public void setProcesses(List<String> processes) {
        this.processes = processes;
    }

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<String> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<String> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<String> softSkills) {
        this.softSkills = softSkills;
    }

    public List<String> getPostulants() {
        return postulants;
    }

    public void setPostulants(List<String> postulants) {
        this.postulants = postulants;
    }

   
}
