/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.request_entities;

/**
 *
 * @author jhanu
 */
public class CreateResponseRecluitersByCompany {

    private String id;
    private String name;
    private int vacants_number_to_care;
    private int postulants_number_to_care;

    public CreateResponseRecluitersByCompany(String id, String name, int vacants_number_to_care, int postulants_number_to_care) {
        this.id = id;
        this.name = name;
        this.vacants_number_to_care = vacants_number_to_care;
        this.postulants_number_to_care = postulants_number_to_care;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVacants_number_to_care() {
        return vacants_number_to_care;
    }

    public void setVacants_number_to_care(int vacants_number_to_care) {
        this.vacants_number_to_care = vacants_number_to_care;
    }

    public int getPostulants_number_to_care() {
        return postulants_number_to_care;
    }

    public void setPostulants_number_to_care(int postulants_number_to_care) {
        this.postulants_number_to_care = postulants_number_to_care;
    }

    @Override
    public String toString() {
        return "CreateRespponseRecluitersByCompany{" + "id=" + id + ", name=" + name + ", vacants_number_to_care=" + vacants_number_to_care + ", postulants_number_to_care=" + postulants_number_to_care + '}';
    }
}
