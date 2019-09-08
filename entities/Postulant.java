/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.io.File;

/**
 *
 * @author seam33
 */
public class Postulant extends  Person {
   private File cv;

    public Postulant(String id, String name, String address, String phone, String email, Byte[] image, String password, String type) {
        super(id, name, address, phone, email, image, password, type);
    }

    public File getCv() {
        return cv;
    }

    public void setCv(File cv) {
        this.cv = cv;
    }

   
}
