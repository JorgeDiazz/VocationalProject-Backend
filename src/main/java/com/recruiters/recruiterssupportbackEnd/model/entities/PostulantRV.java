package com.recruiters.recruiterssupportbackEnd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhanu
 */
@Entity
@Table(name = "postulant_rv")
public class PostulantRV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "state")
    private int state;
    @Column(name = "id_postulant")
    private String idPerson;
    @Column(name = "id_rv")
    private int idVacant;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(String idPerson) {
        this.idPerson = idPerson;
    }

    public int getIdVacant() {
        return idVacant;
    }

    public void setIdVacant(int idVacant) {
        this.idVacant = idVacant;
    }

   

   
}