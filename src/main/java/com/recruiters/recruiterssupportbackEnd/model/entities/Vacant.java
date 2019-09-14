/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhanuar sanchez
 */
@Entity
@Table(name = "person")
public class Vacant {
    @Id
    private int id;
    @Column(name = "place_number")
    private int placeNumber;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "id_job_psotion")
    private String nitJobPosition;

    public Vacant(int id, int placeNumber, Date startDate, Date endDate, String nitJobPosition) {
        this.id = id;
        this.placeNumber = placeNumber;
        this.startDate = startDate;
        this.endDate = endDate;
        this.nitJobPosition = nitJobPosition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlaceNumber() {
        return placeNumber;
    }

    public void setPlaceNumber(int placeNumber) {
        this.placeNumber = placeNumber;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getNitJobPosition() {
        return nitJobPosition;
    }

    public void setNitJobPosition(String nitJobPosition) {
        this.nitJobPosition = nitJobPosition;
    }

    @Override
    public String toString() {
        return "Vacant{" + "id=" + id + ", placeNumber=" + placeNumber + ", startDate=" + startDate + ", endDate=" + endDate + ", nitJobPosition=" + nitJobPosition + '}';
    }
}
