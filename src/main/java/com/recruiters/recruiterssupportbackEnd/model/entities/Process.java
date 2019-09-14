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
 * @author blue
 */
@Entity
@Table(name = "development")
public class Process implements UserEntity{

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "nameD")
    private String name;
     @Column(name = "description")
    private String description;
      @Column(name = "id_job_position")
    private int id_job_position;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_job_position() {
        return id_job_position;
    }

    public void setId_job_position(int id_job_position) {
        this.id_job_position = id_job_position;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", name=" + name + ", description=" + description + ", id_job_position=" + id_job_position + '}';
    }

     
 

}
