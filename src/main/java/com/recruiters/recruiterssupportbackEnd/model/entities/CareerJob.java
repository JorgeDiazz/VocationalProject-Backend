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
@Table(name = "career_job_position")
public class CareerJob {

    @Id
    private String id;
    @Column(name = "id_career")
    private int idCareer;
    @Column(name = "id_job_position")
    private int idjob;

    public int getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(int idCareer) {
        this.idCareer = idCareer;
    }

    public int getIdjob() {
        return idjob;
    }

    public void setIdjob(int idjob) {
        this.idjob = idjob;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
