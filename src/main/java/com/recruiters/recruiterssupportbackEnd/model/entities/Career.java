
package com.recruiters.recruiterssupportbackEnd.model.entities;

import org.springframework.data.annotation.Id;

/**
 *
 * @author seam33
 */

public class Career {
    
    @Id
    private String id;
    private String name;
    private String universityName;
    private String startDate;
    private String endDate;
    private String semester;
    private double universityAverageScore; 

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniversityName() {
        return universityName;
    }

    public void setUniversityName(String universityName) {
        this.universityName = universityName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public double getUniversityAverageScore() {
        return universityAverageScore;
    }

    public void setUniversityAverageScore(double universityAverageScore) {
        this.universityAverageScore = universityAverageScore;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    
}
