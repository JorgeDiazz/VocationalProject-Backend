package com.recruiters.recruiterssupportbackEnd.model.entities;


import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

/**
 *
 * @author Jhanuar Sanchez
 */
public class Vacant {

    @Id
    private String id;
    private String nitCompany;
    private String placesNumber;
    private String startDate;
    private String endDate;
    private JobPosition jobPosition;
    private List<Postulant> postulants;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public String getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(String placesNumber) {
        this.placesNumber = placesNumber;
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

    public JobPosition getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(JobPosition jobPosition) {
        this.jobPosition = jobPosition;
    }

    public List<Postulant> getPostulants() {
        return postulants;
    }

    
    public void setPostulants(List<Postulant> postulants) {
        this.postulants = postulants;
    }
    
    
    public void addPostulants(Postulant postulant) {
        this.postulants.add(postulant);
    }
    
    public void initPostulants(){
        this.postulants = new ArrayList<>();
    }
    
    
    public static boolean isCorrectForCreate(Vacant vacant) {
        return vacant != null && !StringUtils.isEmpty(vacant.getNitCompany()) && !StringUtils.isEmpty(vacant.getPlacesNumber()) && !StringUtils.isEmpty(vacant.getStartDate()) && !StringUtils.isEmpty(vacant.getEndDate());
    }

    @Override
    public String toString() {
        return "Vacant{" + "id=" + id + ", nitCompany=" + nitCompany + ", placesNumber=" + placesNumber + ", startDate=" + startDate + ", endDate=" + endDate + ", jobPosition=" + jobPosition + ", postulants=" + postulants + '}';
    }
  
}
