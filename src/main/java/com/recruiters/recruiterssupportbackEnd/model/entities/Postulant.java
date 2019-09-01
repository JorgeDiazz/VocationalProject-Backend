package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author seam33
 * 
 */

public class Postulant extends Person{
    
    @Id
    private String career;
    private String curriculumVitae;
    private List<Vacant> onHoldVacantList;
    private List<Vacant> inProcessVacantList;
    private List<Vacant> deniedVacantList;

    public String getCareer() {
        return career;
    }

    public void setCareer(String career) {
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

    public List<Vacant> getInProcessVacantList() {
        return inProcessVacantList;
    }

    public void setInProcessVacantList(List<Vacant> inProcessVacantList) {
        this.inProcessVacantList = inProcessVacantList;
    }

    public List<Vacant> getDeniedVacantList() {
        return deniedVacantList;
    }

    public void setDeniedVacantList(List<Vacant> deniedVacantList) {
        this.deniedVacantList = deniedVacantList;
    }
    
}
