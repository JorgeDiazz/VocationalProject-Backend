/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author katemorales
 */
@Entity
@Table(name = "careerP")
public class CareerPerson {
    
    @Column(name = "university")
    @Id
    private String university;
    @Column(name = "aga")
    private double pga;
    @Column(name = "semester")
    private int semester;
    @Column(name = "id_person")
    private String idPerson;
    @Column(name = "id_career")
    private int idCareer;

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public double getPga() {
        return pga;
    }

    public void setPga(double pga) {
        this.pga = pga;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(int idCareer) {
        this.idCareer = idCareer;
    }
    
    
}
