package com.recruiters.recruiterssupportbackEnd.controller.response_entities;

import java.util.ArrayList;
import java.util.List;

public class GetPostulantsWithoutRecruiter {

    private String id;
    private String name;
    private List<String> careers;

    public GetPostulantsWithoutRecruiter() {
        this.careers = new ArrayList<>();
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

    public List<String> getCareers() {
        return careers;
    }

    public void setCareers(List<String> careers) {
        this.careers = careers;
    }

}
