package com.recruiters.recruiterssupportbackEnd.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhanuar Sanchez
 */
@Entity
@Table(name = "careerp")
public class CareerP{
    
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private int id;
    @Column(name = "university")
    private String university;
    @Column(name = "aga")
    private Double aga;
    @Column(name = "semester")
    private int semester;
    @Column(name = "id_person")
    private String idPerson;
    @Column(name = "id_career")
    private int idCareer;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public double getAga() {
        return aga;
    }

    public void setAga(double aga) {
        this.aga = aga;
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
