/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

public class Company {

    @Id
    private String id;
    private String nit;
    private String name;
    private String email;
    private String number;
    private Byte[] image;
    private String password;
    private String address;
    private boolean active;
    private List<Recruiter> recruiters;
    private List<SoftSkill> softSkills;
    private List<JobPosition> JobsPositions;

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void setRecruiters(List<Recruiter> recruiters) {
        this.recruiters = recruiters;
    }

    public void addRecruiters(Recruiter recruiter) {
        this.recruiters.add(recruiter);
    }

    public List<SoftSkill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<SoftSkill> softSkills) {
        this.softSkills = softSkills;
    }

    public List<JobPosition> getJobsPositions() {
        return JobsPositions;
    }

    public void setJobsPositions(List<JobPosition> JobsPositions) {
        this.JobsPositions = JobsPositions;
    }

    public void initRecruiters(){
        this.recruiters = new ArrayList<>();
    }
    
    public static boolean isCorrectForCreate(Company company) {
        return company != null && !StringUtils.isEmpty(company.getNit()) && !StringUtils.isEmpty(company.getName()) && !StringUtils.isEmpty(company.getEmail()) && !StringUtils.isEmpty(company.getNumber()) && !StringUtils.isEmpty(company.getAddress());
    }

    @Override
    public String toString() {
        return "Company{" + "id=" + id + ", nit=" + nit + ", name=" + name + ", email=" + email + ", number=" + number + ", image=" + image + ", password=" + password + ", address=" + address + ", active=" + active + ", recruiters=" + recruiters + ", softSkills=" + softSkills + ", JobsPositions=" + JobsPositions + '}';
    }
    
   
    
}