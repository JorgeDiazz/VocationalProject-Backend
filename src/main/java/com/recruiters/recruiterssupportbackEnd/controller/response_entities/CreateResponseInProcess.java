/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jhanu
 */
public class CreateResponseInProcess {

private int id;    

private Double salaryMin;
private Double salaryMax;
private String description;
private String nameArea;
private List<String> careers;
private List<String> hardSkills;
private List<String> softSkill;
private List<String> processes;
int id_rv;
private Vacant vacant;
private List<CreateResponsePostulant> postulants;

    public CreateResponseInProcess() {
        this.postulants=new ArrayList<>();
        this.hardSkills=new ArrayList<>();
        this.softSkill=new ArrayList<>();
        this.processes=new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public List<String> getCareers() {
        return careers;
    }

    public void setCareers(List<String> careers) {
        this.careers = careers;
    }

    public List<String> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<String> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<String> getProcesses() {
        return processes;
    }

    public void setProcesses(List<String> processes) {
        this.processes = processes;
    }

    public List<String> getSoftSkill() {
        return softSkill;
    }

    public void setSoftSkill(List<String> softSkill) {
        this.softSkill = softSkill;
    }
 

    public int getId_rv() {
        return id_rv;
    }

    public void setId_rv(int id_rv) {
        this.id_rv = id_rv;
    }

    public Vacant getVacant() {
        return vacant;
    }

    public void setVacant(Vacant vacant) {
        this.vacant = vacant;
    }

    public List<CreateResponsePostulant> getPostulants() {
        return postulants;
    }

    public void setPostulants(List<CreateResponsePostulant> postulants) {
        this.postulants = postulants;
    }

  
}
