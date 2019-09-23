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
public class CreateCompanyRequest {

    private String name;
   private String email;
  private String  nit;
  private String phone;

    public CreateCompanyRequest(String name, String email, String nit, String phone) {
        this.name = name;
        this.email = email;
        this.nit = nit;
        this.phone = phone;
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

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    
}
