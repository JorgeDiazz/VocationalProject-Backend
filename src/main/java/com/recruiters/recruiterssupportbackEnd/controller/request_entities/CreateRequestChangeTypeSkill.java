package com.recruiters.recruiterssupportbackEnd.controller.request_entities;


public class CreateRequestChangeTypeSkill {
    
    private int id;
    private String nitCompany;
    private String newType;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getNewType() {
        return newType;
    }

    public void setNewType(String newType) {
        this.newType = newType;
    }
    
}
