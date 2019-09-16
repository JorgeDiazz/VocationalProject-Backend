package com.recruiters.recruiterssupportbackEnd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author katemorales
 */

@Entity
@Table(name = "career_job_position")
public class CareerJob {

    @Id
    private String id;
    @Column(name = "id_career")
    private int idCareer;
    @Column(name = "id_job_position")
    private int idJob;

    public int getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(int idCareer) {
        this.idCareer = idCareer;
    }

    public int getIdjob() {
        return idJob;
    }

    public void setIdjob(int idJob) {
        this.idJob = idJob;
    }

    public String getId() {
        return id;
    }

    public void setId(String idJob, String idCareer) {
        this.id = idJob+idCareer;
    }
    
    
}
