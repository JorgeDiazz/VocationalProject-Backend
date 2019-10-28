/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

/**
 *
 * @author jhanu
 */
public class CreateRequestJobRec {
    private int idJob;
    private String idRecruiter;        

    public CreateRequestJobRec(int idJob, String idRecruiter) {
        this.idJob = idJob;
        this.idRecruiter = idRecruiter;
    }

    public int getIdJob() {
        return idJob;
    }

    public void setIdJob(int idJob) {
        this.idJob = idJob;
    }

    public String getIdRecruiter() {
        return idRecruiter;
    }

    public void setIdRecruiter(String idRecruiter) {
        this.idRecruiter = idRecruiter;
    }
            

}
