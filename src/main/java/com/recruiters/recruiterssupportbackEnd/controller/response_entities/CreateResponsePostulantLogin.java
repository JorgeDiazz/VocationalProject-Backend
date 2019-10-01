package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import java.util.List;

/**
 *
 * @author jhanu
 */
public class CreateResponsePostulantLogin implements UserEntity {
    
    private String id;
    private String name;
    private String email;
    private List careers;//lista de id de carrera

    public CreateResponsePostulantLogin(String id, String name, String email, List careers) {
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

    public List getCareers() {
        return careers;
    }

    public void setCareers(List careers) {
        this.careers = careers;
    }

    
}
