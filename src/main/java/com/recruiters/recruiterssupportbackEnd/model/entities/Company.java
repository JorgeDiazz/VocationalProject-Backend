package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "company")
public class Company implements UserEntity {

    @Id
    private String nit;
    @Column(name = "nameC")
    private String name;
    private String address;
    private String phone;
    private String email;
    private Byte[] image;
    @JsonIgnore
    @Column(name = "_password")
    private String password;
    @Transient
    private List<Person> persons;
    @Transient
    private List<Skill> globalSkills;
    @Transient
    private List<JobPosition> JobsPositions;
    @Transient
    private TYPE type;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<JobPosition> getJobsPositions() {
        return JobsPositions;
    }

    public void setJobsPositions(List<JobPosition> JobsPositions) {
        this.JobsPositions = JobsPositions;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Company{" + "nit=" + nit + ", name=" + name + ", address=" + address + ", phone=" + phone + ", email=" + email + ", image=" + image + ", password=" + password + ", persons=" + persons + ", globalSkills=" + globalSkills + ", JobsPositions=" + JobsPositions + '}';
    }

}
