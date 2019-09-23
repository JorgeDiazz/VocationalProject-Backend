/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;

/**
 *
 * @author jhanu
 */
public class CreateResponsePostulantLogin implements UserEntity {
    
    private String id;
    private String name;
    private String email;
    private int[] careers;//lista de id de carrera

    public CreateResponsePostulantLogin(String id, String name, String email, int[] careers) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.careers = careers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int[] getCareers() {
        return careers;
    }

    public void setCareers(int[] careers) {
        this.careers = careers;
    }
}
