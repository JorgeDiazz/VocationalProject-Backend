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
public class CreateResponseCompanyLogin implements UserEntity{

    private String nit;
    private String name;
    private String address;
    private String phone;
    private String image;
    private String email;
    private String type;

    public CreateResponseCompanyLogin(String nit, String name, String address, String phone, String imageurl, String email, String type) {
        this.nit = nit;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.image = imageurl;
        this.email = email;
        this.type = type;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String imageurl) {
        this.image = imageurl;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
