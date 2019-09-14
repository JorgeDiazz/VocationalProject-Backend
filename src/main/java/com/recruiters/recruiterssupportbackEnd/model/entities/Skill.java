package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.Result;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author seam33
 */
@Entity
@Table(name = "skill")
public class Skill {
    
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Column(name = "nameSK")
    private String name;
    @Column(name = "class")
    private String type;
    @Column(name = "id_job_position")
    private int id_job_position;

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

    public int getId_job_position() {
        return id_job_position;
    }

    public void setId_job_position(int id_job_position) {
        this.id_job_position = id_job_position;
    }

    @Override
    public String toString() {
        return "Skill{" + "id=" + id + ", name=" + name + ", type=" + type + ", id_job_position=" + id_job_position + '}';
    }

   
    
}
