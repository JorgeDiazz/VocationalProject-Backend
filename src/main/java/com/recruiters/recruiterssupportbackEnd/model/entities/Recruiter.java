package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.ArrayList;
import java.util.List;
import org.springframework.util.StringUtils;

public class Recruiter extends Person {

    private String nitCompany;
    private List<Vacant> pendingVacant;
    private List<Vacant> acceptedVacant;

    public List<Vacant> getPendingVacant() {
        return pendingVacant;
    }

    public void setPendingVacant(List<Vacant> pendingVacant) {
        this.pendingVacant = pendingVacant;
    }

    public void addPendingVacant(Vacant pendingVacant) {
        this.pendingVacant.add(pendingVacant);
    }

    public void initPendingVacant() {
        this.pendingVacant = new ArrayList<>();
    }

    public List<Vacant> getAcceptedVacant() {
        return acceptedVacant;
    }

    public void setAcceptedVacant(List<Vacant> acceptedVacant) {
        this.acceptedVacant = acceptedVacant;
    }

    public void addacceptedVacant(Vacant acceptedVacant) {
        this.acceptedVacant.add(acceptedVacant);
    }

    public void initacceptedVacant() {
        this.acceptedVacant = new ArrayList<>();
    }

    public String getNitCompany() {
        return nitCompany;
    }

    public void setNitCompany(String nitCompany) {
        this.nitCompany = nitCompany;
    }

    public static boolean isCorrectForCreate(Recruiter recruiter) {
        return recruiter != null && !StringUtils.isEmpty(recruiter.getEmail()) && !StringUtils.isEmpty(recruiter.getNitCompany());
    }
}
