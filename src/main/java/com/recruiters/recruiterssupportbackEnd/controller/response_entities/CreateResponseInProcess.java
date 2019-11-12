/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ResponseGetSpecificJobPosition.Skill;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ResponseGetSpecificJobPosition.Vacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author jhanu
 */
public class CreateResponseInProcess {

    private int id;
    private String name;
    private Double salaryMin;
    private Double salaryMax;
    private String description;
    private int idArea;
    private String nameArea;
    private List<Career> careers;
    private List<Skill> softSkills;
    private List<Skill> hardSkills;
    private List<ResponseGetSpecificJobPosition.Process> processes;
    private Vacant vacant;
    private List<Postulant> Postulants;

    public CreateResponseInProcess() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getIdArea() {
        return idArea;
    }

    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }

    public String getNameArea() {
        return nameArea;
    }

    public void setNameArea(String nameArea) {
        this.nameArea = nameArea;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public List<Skill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<Skill> softSkills) {
        this.softSkills = softSkills;
    }

    public List<Skill> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<Skill> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<ResponseGetSpecificJobPosition.Process> getProcesses() {
        return processes;
    }

    public void setProcesses(List<ResponseGetSpecificJobPosition.Process> processes) {
        this.processes = processes;
    }

    public Vacant getVacant() {
        return vacant;
    }

    public void setVacant(Vacant vacant) {
        this.vacant = vacant;
    }

    public List<Postulant> getPostulants() {
        return Postulants;
    }

    public void setPostulants(List<Postulant> Postulants) {
        this.Postulants = Postulants;
    }

    

   

    static public class Postulant {

        private String id;
        private String name;

        public Postulant(String id, String name) {
            this.id = id;
            this.name = name;
        }

        public Postulant() {
        }
        

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    
     static public class Vacant {

        private int placesNumber;
        private Date startDate;


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
    }
}
