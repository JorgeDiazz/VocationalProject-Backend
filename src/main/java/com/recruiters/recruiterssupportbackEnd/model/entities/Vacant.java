/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.Date;
import java.util.List;

/**
 *
 * @author seam33
 */
public class Vacant {

    private int id;
    private int placeNumber;
    private Date startDate;
    private Date endDate;
    private List<Postulant> postulans;

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

    @Override
    public String toString() {
        return "Vacant{" + "id=" + id + ", placeNumber=" + placeNumber + ", startDate=" + startDate + ", endDate=" + endDate + ", postulans=" + postulans + '}';
    }

}
