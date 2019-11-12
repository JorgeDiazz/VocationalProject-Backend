/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;

/**
 *
 * @author Jhoan Saavedra
 * @Date 29/10/2019
 */
public class CreateRequestRecruiterVacant implements UserEntity {

    String idPerson;
    int idVacant;

   
    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdVacant() {
        return idVacant;
    }

    public void setIdVacant(int idVacant) {
        this.idVacant = idVacant;
    }

}
