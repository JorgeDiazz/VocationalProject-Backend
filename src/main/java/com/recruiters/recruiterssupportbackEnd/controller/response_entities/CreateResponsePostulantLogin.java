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
    private String image;
    private List careers;//lista de id de carrera
    private String type;

    public CreateResponsePostulantLogin(String id, String name, String email, String image, List careers, String type) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.image = image;
        this.careers = careers;
        this.type = type;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List getCareers() {
        return careers;
    }

    public void setCareers(List careers) {
        this.careers = careers;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
