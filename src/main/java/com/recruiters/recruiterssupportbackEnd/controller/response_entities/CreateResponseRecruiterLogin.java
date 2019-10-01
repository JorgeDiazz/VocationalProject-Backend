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
public class CreateResponseRecruiterLogin implements UserEntity{
    
    private String id;
    private String name;
    private String email;
    private String nitCompany;
    private String nameCompany;

    public CreateResponseRecruiterLogin(String id, String name, String email, String nitCompany, String nameCompany) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.nitCompany = nitCompany;
        this.nameCompany = nameCompany;
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

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getNameCompany() {
        return nameCompany;
    }

    public void setNameCompany(String nameCompany) {
        this.nameCompany = nameCompany;
    }

}
