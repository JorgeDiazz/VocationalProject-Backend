package com.recruiters.recruiterssupportbackEnd.model.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postulant")
public class Postulant {

    @Id
    @Column(name = "id_person")
    private String idPerson;
    @Column(name = "address")
    private String address;
    @Column(name = "cv")
    private String cv;
    @Column(name = "class")
    private String type;

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Postulant{" + "idPerson=" + idPerson + ", address=" + address + ", cv=" + cv + ", type=" + type + '}';
    }
    
}
