package com.recruiters.recruiterssupportbackEnd.model.entities.skills;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Jhanuar Sanchez
 */
@Entity
@Table(name = "skill")
public class Skill {
    
    @Id
    private int id;
    @Column(name = "nameSK")
    private String name;
    @Column(name = "class")
    private String type;
    @Column(name = "id_job_psotion")
    private String nitJobPosition;

    public Skill(int id, String name, String type, String nitJobPosition) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.nitJobPosition = nitJobPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNitJobPosition() {
        return nitJobPosition;
    }

    public void setNitJobPosition(String nitJobPosition) {
        this.nitJobPosition = nitJobPosition;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", name=" + name + ", type=" + type + ", nitJobPosition=" + nitJobPosition + '}';
    }

   
}
