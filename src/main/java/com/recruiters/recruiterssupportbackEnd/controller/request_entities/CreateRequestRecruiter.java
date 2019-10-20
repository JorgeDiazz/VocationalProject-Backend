/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

/**
 *
 * @author seam33
 */
public class CreateRequestRecruiter {
    
    private String nitCompany;
    private String email;
    private String id;

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nit) {
        this.nitCompany = nit;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }  
}
