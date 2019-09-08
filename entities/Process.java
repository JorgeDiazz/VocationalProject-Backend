/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;

/**
 *
 * @author blue
 */
public class Process {
    
    private int id;
    private String name;
    private String description;
    private List<Result> results;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public Process(int id, String name, String description, List<Result> results) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.results = results;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", name=" + name + ", description=" + description + ", results=" + results + '}';
    }
    
    
}
