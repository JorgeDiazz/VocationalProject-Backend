/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

/**
 *
 * @author jhanu
 */
public class CreateResponseRecruitersByCompany {

    private String id;
    private String name;
    private int vacantsNumber;
    private int postulantsNumber;

    public CreateResponseRecruitersByCompany(String id, String name, int vacantsNumber, int postulantsNumber) {
        this.id = id;
        this.name = name;
        this.vacantsNumber = vacantsNumber;
        this.postulantsNumber = postulantsNumber;
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

    public int getVacantsNumber() {
        return vacantsNumber;
    }

    public void setVacantsNumber(int vacantsNumber) {
        this.vacantsNumber = vacantsNumber;
    }

    public int getPostulantsNumber() {
        return postulantsNumber;
    }

    public void setPostulantsNumber(int postulantsNumber) {
        this.postulantsNumber = postulantsNumber;
    }

    @Override
    public String toString() {
        return "CreateResponseRecluitersByCompany{" + "id=" + id + ", name=" + name + ", vacantsNumber=" + vacantsNumber + ", postulantsNumber=" + postulantsNumber + '}';
    }
}
