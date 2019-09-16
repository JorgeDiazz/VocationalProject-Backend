
package com.recruiters.recruiterssupportbackEnd.model.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "area")
public class Area{
    
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "nit_company")
    private String nit_company;

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

    public String getNit_company() {
        return nit_company;
    }

    public void setNit_company(String nit_company) {
        this.nit_company = nit_company;
    }
    
}
