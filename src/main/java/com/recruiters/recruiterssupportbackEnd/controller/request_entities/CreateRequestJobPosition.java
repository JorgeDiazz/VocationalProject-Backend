package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.Processs;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import java.util.List;

/**
 *
 * @author seam33
 */

public class CreateRequestJobPosition {
    
    private String nitCompany;
    private String name;
    private double salaryMin;
    private double salaryMax;
    private int idArea;
    private String description;
    private List<Career> career;
    private List<Career> newCareer;
    private List<Skill> hardSkill;
    private List<Skill> newHardSkill;
    private List<Processs> processs;
    private List<Person> recruiter;
    private int placeNumber;

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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Career> getCareer() {
        return career;
    }

    public void setCareer(List<Career> career) {
        this.career = career;
    }

    public List<Career> getNewCareer() {
        return newCareer;
    }

    public void setNewCareer(List<Career> newCareer) {
        this.newCareer = newCareer;
    }

    public List<Skill> getHardSkill() {
        return hardSkill;
    }

    public void setHardSkill(List<Skill> hardSkill) {
        this.hardSkill = hardSkill;
    }

    public List<Skill> getNewHardSkill() {
        return newHardSkill;
    }

    public void setNewHardSkill(List<Skill> newHardSkill) {
        this.newHardSkill = newHardSkill;
    }

    public List<Processs> getProcess() {
        return processs;
    }

    public void setProcess(List<Processs> processs) {
        this.processs = processs;
    }

    public List<Person> getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(List<Person> recruiter) {
        this.recruiter = recruiter;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    @Override
    public String toString() {
        return "CreateRequestJobPosition{" + "nitCompany=" + nitCompany + ", name=" + name + ", salaryMin=" + salaryMin + ", salaryMax=" + salaryMax + ", idArea=" + idArea + ", description=" + description + ", career=" + career + ", newCareer=" + newCareer + ", hardSkill=" + hardSkill + ", newHardSkill=" + newHardSkill + ", process=" + processs + ", recruiter=" + recruiter + ", placeNumber=" + placeNumber + '}';
    } 
}
