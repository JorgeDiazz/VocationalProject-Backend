package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.util.Strings;

/**
 *
 * @author seam33
 * 
 */

public class Postulant extends Person{
    
    private Career career;
    private String curriculumVitae;
    private List<Vacant> onHoldVacantList;
    private List<Vacant> inProcessVacantList;
    private List<Vacant> deniedVacantList;
    
    public Career getCareer() {
        return career;
    }

    public void setCareer(Career career) {
        this.career = career;
    }

    public String getCurriculumVitae() {
        return curriculumVitae;
    }

    public void setCurriculumVitae(String curriculumVitae) {
        this.curriculumVitae = curriculumVitae;
    }

    public List<Vacant> getOnHoldVacantList() {
        return onHoldVacantList;
    }

    public void setOnHoldVacantList(List<Vacant> onHoldVacantList) {
        this.onHoldVacantList = onHoldVacantList;
    }
    
    public void addOnHoldVacantList(Vacant onHoldVacantList){
        this.onHoldVacantList.add(onHoldVacantList);
    }
    
    public void initOnHoldVacantList(){
        this.onHoldVacantList=new ArrayList<>();
    }

    public List<Vacant> getInProcessVacantList() {
        return inProcessVacantList;
    }

    public void setInProcessVacantList(List<Vacant> inProcessVacantList) {
        this.inProcessVacantList = inProcessVacantList;
    }

    public void addInProcessVacantList(Vacant inProcessVacantList){
        this.inProcessVacantList.add(inProcessVacantList);
    }
    
     public void initInProcessVacantList(){
        this.inProcessVacantList=new ArrayList<>();
    }
    
    public List<Vacant> getDeniedVacantList() {
        return deniedVacantList;
    }

    public void setDeniedVacantList(List<Vacant> deniedVacantList) {
        this.deniedVacantList = deniedVacantList;
    }

    public void initCareer(){
        this.career=new Career();
    }
    
    public static boolean isCorrectForCreate(Postulant postulant){
        return postulant!=null && !Strings.isEmpty(postulant.getName()) && !Strings.isEmpty(postulant.getEmail()) && !Strings.isEmpty(postulant.getCareer().getName()); 
    }

}
