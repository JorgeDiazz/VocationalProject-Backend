package com.recruiters.recruiterssupportbackEnd.model.entities;


import com.recruiters.recruiterssupportbackEnd.model.entities.skills.Skill;
import java.util.List;

/**
 *
 * @author seam33
 */
public class JobPosition {
    
    private int id;
    private String name;
    private Double salaryMin;
    private Double salaryMax;
    private String description;
    private List<Vacant> vacants;
    private List<Career> carrers;
    private List<Skill> skill;
    private Process process;
    

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "JobPosition{" + "id=" + id + ", name=" + name + ", salaryMin=" + salaryMin + ", salaryMax=" + salaryMax + ", description=" + description + ", vacants=" + vacants + ", carrers=" + carrers + ", skill=" + skill + ", process=" + process + '}';
    }

    
    
}
