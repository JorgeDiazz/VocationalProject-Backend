package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

public class Recruiter {

    @Id
    private String id;
    private String nitCompany;
    private String name;
    private String email;
    private String password;
    private Byte[] image;
    private List<Vacant> vacant;

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

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public static boolean isCorrectForCreate(Recruiter recruiter) {
        return recruiter != null && !StringUtils.isEmpty(recruiter.getEmail()) && !StringUtils.isEmpty(recruiter.getNitCompany());
    }
}
