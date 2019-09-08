package com.recruiters.recruiterssupportbackEnd.model.entities.skills;

import com.recruiters.recruiterssupportbackEnd.model.entities.Result;

/**
 *
 * @author seam33
 */

public class Skill {
    
    private int id;
    private String name;
    private String type;
    private Result results;

    public Skill(int id, String name, String type, Result results) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.results = results;
    }
    
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Result getResults() {
        return results;
    }

    public void setResults(Result results) {
        this.results = results;
    }

    
@Override
    public String toString() {
        return "Skill{" + "id=" + id + ", name=" + name + ", type=" + type + ", results=" + results + '}';
    }
   
    
}
