/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author Jhanuar Sanchez
 */
public class Vacant {
    
     @Id
    private String id;
    private String nit;
    private String nitcompany;
    private String numberplaces;
    private String startdate;
    private String enddate;
    private JobPosition jobposition;
    private List<Postulant> postulants;;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getNitcompany() {
        return nitcompany;
    }

    public void setNitcompany(String nitcompany) {
        this.nitcompany = nitcompany;
    }

    public String getNumberplaces() {
        return numberplaces;
    }

    public void setNumberplaces(String numberplaces) {
        this.numberplaces = numberplaces;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public JobPosition getJobposition() {
        return jobposition;
    }

    public void setJobposition(JobPosition jobposition) {
        this.jobposition = jobposition;
    }

    public List<Postulant> getPostulants() {
        return postulants;
    }

    public void setPostulants(List<Postulant> postulants) {
        this.postulants = postulants;
    }
    
    
    
    
}
