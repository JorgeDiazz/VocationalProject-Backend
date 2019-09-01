package com.recruiters.recruiterssupportbackEnd.model.entities;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;

public class Recruiter extends Person{

    @Id
    private String nitCompany;
    private List<Vacant> vacant;

    public List<Vacant> getVacant() {
        return vacant;
    }

    public void setVacant(List<Vacant> vacant) {
        this.vacant = vacant;
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
