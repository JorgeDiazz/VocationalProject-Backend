/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

/**
 *
 * @author katemorales
 */
public class CreateRequestProcess {
    private String name;
    private int id_job_position;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId_job_position() {
        return id_job_position;
    }

    public void setId_job_position(int id_job_position) {
        this.id_job_position = id_job_position;
    }
    
    
}
