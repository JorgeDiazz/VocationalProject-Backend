package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

public class SelectPostulantsObject {

    private int idVacant;
    private String[] postulants;
    private int idRv;

    public int getIdVacant() {
        return idVacant;
    }

    public void setIdVacant(int idVacant) {
        this.idVacant = idVacant;
    }

    public String[] getPostulants() {
        return postulants;
    }

    public void setPostulants(String[] postulants) {
        this.postulants = postulants;
    }

    public int getIdRv() {
        return idRv;
    }

    public void setIdRv(int idRv) {
        this.idRv = idRv;
    }

}
