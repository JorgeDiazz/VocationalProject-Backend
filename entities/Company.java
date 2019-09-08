/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;


import com.recruiters.recruiterssupportbackEnd.model.entities.skills.Skill;
import java.util.List;



/**
 *
 * @author seam33
 */
public class Company {
    
    

    private String nit;
    private String name;
    private String address;
    private String phone;
    private String email;
    private Byte[] image;
    private String password;
    private List<Person> persons;
    private List<Skill> globalSkills;
    private List<JobPosition> JobsPositions;

    public Company(String nit, String name, String address, String phone, String email, Byte[] image, String password, List<Person> persons, List<Skill> globalSkills, List<JobPosition> JobsPositions) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.image = image;
        this.password = password;
        this.persons = persons;
        this.globalSkills = globalSkills;
        this.JobsPositions = JobsPositions;
    }
 

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public List<JobPosition> getJobsPositions() {
        return JobsPositions;
    }

    public void setJobsPositions(List<JobPosition> JobsPositions) {
        this.JobsPositions = JobsPositions;
    }

    @Override
    public String toString() {
        return "Company{" + "nit=" + nit + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", image=" + image + ", password=" + password + ", persons=" + persons + ", globalSkills=" + globalSkills + ", JobsPositions=" + JobsPositions + '}';
    }
   
}
