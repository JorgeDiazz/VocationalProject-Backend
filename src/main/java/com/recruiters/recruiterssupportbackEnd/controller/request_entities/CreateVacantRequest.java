package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

import java.util.List;
import javax.persistence.Column;

/**
 *
 * @author JorgeDíaz
 */
public class CreateVacantRequest {

    @Column(name = "places_number")
    private int placesNumber;

    @Column(name = "id_job_position")
    private int idJobPosition;

    private List<String> postulantsId;

    public List<String> getPostulantsId() {
        return postulantsId;
    }

    public void setPostulantsId(List<String> postulantsId) {
        this.postulantsId = postulantsId;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
        this.placesNumber = placesNumber;
    }

    public int getIdJobPosition() {
        return idJobPosition;
    }

    public void setIdJobPosition(int idJobPosition) {
        this.idJobPosition = idJobPosition;
    }

}