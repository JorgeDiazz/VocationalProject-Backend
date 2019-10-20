package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author JorgeDíaz
 */
public class ResponseGetJobPositionByNit {

    private int idJobPosition;
    private List<Vacant> vacants;
    private String name;
    private Double salaryMin;
    private Double salaryMax;

    public ResponseGetJobPositionByNit() {
        this.vacants = new ArrayList<>();
    }

    public List<Vacant> getVacants() {
        return vacants;
    }

    public void setVacants(List<Vacant> vacants) {
        this.vacants = vacants;
    }

    public int getIdJobPosition() {
        return idJobPosition;
    }

    public void setIdJobPosition(int idJobPosition) {
        this.idJobPosition = idJobPosition;
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

    static public class Vacant {

        private int id;
        private int placesNumber;

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
    }

}
