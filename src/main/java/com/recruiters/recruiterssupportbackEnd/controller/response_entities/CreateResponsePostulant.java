/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

/**
 *
 * @author Proximate
 */
public class CreateResponsePostulant {
    
     private String id;
     private String name;
     private String image;
     private String email;

        public CreateResponsePostulant(String id, String name, String image,String email) {
            this.id = id;
            this.name = name;
            this.image = image;
            this.email=email;
        }

        public CreateResponsePostulant() {
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

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
