package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author seam33
 */
@Entity
public class VacantByCareer {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "id_career")
    private int idCareer;
    @Column(name = "name")
    private String name;
    @Column(name = "salary_min")
    private Double salaryMin;
    @Column(name = "salary_max")
    private Double salaryMax;
    @Column(name = "start_date")
    private Date startDate;
    @Column(name = "places_number")
    private int placesNumber;

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

    public Double getSalaryMin() {
        return salaryMin;
    }

    public void setSalaryMin(Double salaryMin) {
        this.salaryMin = salaryMin;
    }

    public Double getSalaryMax() {
        return salaryMax;
    }

    public void setSalaryMax(Double salaryMax) {
        this.salaryMax = salaryMax;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public int getIdCareer() {
        return idCareer;
    }

    public void setIdCareer(int idCareer) {
        this.idCareer = idCareer;
    }

}
