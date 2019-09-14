/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author katemorales
 */
@Entity
@Table(name = "jb_skill")
public class SkillJob implements UserEntity {
    
    @Id
    private String id;
    @Column(name = "id_job_position")
    private int idJob;
    @Column(name = "id_skill")
    private int idSkill;

    public String getId() {
        return id;
    }

    public void setId(String idJob,String idSkill) {
        this.id = idJob+idSkill;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }
    
    
}
