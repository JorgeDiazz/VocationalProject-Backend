package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author seam33
 */

public class Recruiter {

    @Id
    private String id;
    private String name;
    private String email;
    private String password;
    private Byte[] image;
    private List<Vacant> vacant;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public List<Vacant> getVacant() {
        return vacant;
    }

    public void setVacant(List<Vacant> vacant) {
        this.vacant = vacant;
    } 
    
}
