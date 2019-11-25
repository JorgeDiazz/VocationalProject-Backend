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
    private int idVacant;
    private String idRecruiter;        

    public CreateRequestJobRec(int idVacant, String idRecruiter) {
        this.idVacant = idVacant;
        this.idRecruiter = idRecruiter;
    }

    public int getIdVacant() {
        return idVacant;
    }

    public void setIdVacant(int idVacant) {
        this.idVacant = idVacant;
    }

    public String getIdRecruiter() {
        return idRecruiter;
    }

    public void setIdRecruiter(String idRecruiter) {
        this.idRecruiter = idRecruiter;
    }

    @Override
    public String toString() {
        return "CreateRequestJobRec{" + "idVacant=" + idVacant + ", idRecruiter=" + idRecruiter + '}';
    }
         

}
