/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author jhanuar sanchez
 */
@Entity
@Table(name = "vacant")
public class Vacant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "places_number")
    private int placesNumber;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "id_job_position")
    private int nitJobPosition;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
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

    public int getNitJobPosition() {
        return nitJobPosition;
    }

    public void setNitJobPosition(int nitJobPosition) {
        this.nitJobPosition = nitJobPosition;
    }

    @Override
    public String toString() {
        return "Vacant{" + "id=" + id + ", placeNumber=" + placesNumber + ", startDate=" + startDate + ", endDate=" + endDate + ", nitJobPosition=" + nitJobPosition + '}';
    }
}
