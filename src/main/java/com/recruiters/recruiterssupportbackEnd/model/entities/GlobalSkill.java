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
@Table(name = "global_skill")
public class GlobalSkill{
    
    @Id
    private String id;  
    @Column(name = "nitCompany")
    private String nit;
    @Column(name = "id_skill")
    private int idSkill;

    public String getId() {
        return id;
    }

    public void setId(String nit,String idSkill) {
        this.id = nit+idSkill;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public int getIdSkill() {
        return idSkill;
    }

    public void setIdSkill(int idSkill) {
        this.idSkill = idSkill;
    }
    
    
}
