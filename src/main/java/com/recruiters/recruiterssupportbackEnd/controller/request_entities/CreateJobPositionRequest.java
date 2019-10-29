package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

/**
 *
 * @author JorgeDíaz
 */
public class CreateJobPositionRequest {

    private String nitCompany;
    private String name;
    private double salaryMin;
    private double salaryMax;
    private String description;
    private int idArea;
    private int[] careersId;
    private String[] newCareersName;
    private int[] hardSkillsId;
    private String[] newHardSkillsName;
    private String[] processesName;
    private String[] recruitersId;
    
    private int[] softSkillsId; //By Jhoan 20/10/2019
    
    public int[] getSoftSkillsId() {
        return softSkillsId;
    }

    public void setSoftSkillsId(int[] softSkillsId) {
        this.softSkillsId = softSkillsId;
    }
    private int placesNumber;

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public int[] getCareersId() {
        return careersId;
    }

    public void setCareersId(int[] careersId) {
        this.careersId = careersId;
    }

    public String[] getNewCareersName() {
        return newCareersName;
    }

    public void setNewCareersName(String[] newCareersName) {
        this.newCareersName = newCareersName;
    }

    public int[] getHardSkillsId() {
        return hardSkillsId;
    }

    public void setHardSkillsId(int[] hardSkillsId) {
        this.hardSkillsId = hardSkillsId;
    }

    public String[] getNewHardSkillsName() {
        return newHardSkillsName;
    }

    public void setNewHardSkillsName(String[] newHardSkillsName) {
        this.newHardSkillsName = newHardSkillsName;
    }

    public String[] getProcessesName() {
        return processesName;
    }

    public void setProcessesName(String[] processesName) {
        this.processesName = processesName;
    }

    public String[] getRecruitersId() {
        return recruitersId;
    }

    public void setRecruitersId(String[] recruitersId) {
        this.recruitersId = recruitersId;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

}
