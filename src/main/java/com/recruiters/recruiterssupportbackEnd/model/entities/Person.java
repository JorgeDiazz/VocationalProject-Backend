package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")
public class Person implements UserEntity {

    
    @Id
    private String id;
    @Column(name = "nameP")
    private String name;
    private String email;
    private Byte[] image;
    @JsonIgnore
    @Column(name = "_password")
    private String password;
    @Column(name = "class")
    private String type;
    @Column(name = "nit_company")
    private String nitCompany;

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
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

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Person{" + "id=" + id + ", name=" + name + ", email=" + email + ", image=" + image + ", password=" + password + ", type=" + type + ", nitCompany=" + nitCompany + '}';
    }

}
