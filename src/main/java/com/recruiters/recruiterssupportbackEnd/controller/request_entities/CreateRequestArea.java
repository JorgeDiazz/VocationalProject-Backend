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
public class CreateRequestArea {
    private String nitCompany;
    private String name;

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
