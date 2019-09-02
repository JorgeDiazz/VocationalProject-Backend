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
    private String nameJobPosition;
    private int placesNumber;
    private String startDate;
    private String endDate;
    private List<Postulant> postulants;

   
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameJobPosition() {
        return nameJobPosition;
    }

    public void setNameJobPosition(String nameJobPosition) {
        this.nameJobPosition = nameJobPosition;
    }

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public int getPlacesNumber() {
        return placesNumber;
    }

    public void setPlacesNumber(int placesNumber) {
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
        return vacant != null && !StringUtils.isEmpty(vacant.getNitCompany()) && !StringUtils.isEmpty(vacant.getNameJobPosition()) && !StringUtils.isEmpty(vacant.getPlacesNumber()) && !StringUtils.isEmpty(vacant.getStartDate()) && !StringUtils.isEmpty(vacant.getEndDate());
    }
}
