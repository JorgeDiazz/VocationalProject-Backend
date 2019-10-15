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

    @Column(name = "recruiters_id")
    private List<String> recruitersId;

    public List<String> getRecruitersId() {
        return recruitersId;
    }

    public void setRecruitersId(List<String> recruitersId) {
        this.recruitersId = recruitersId;
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
