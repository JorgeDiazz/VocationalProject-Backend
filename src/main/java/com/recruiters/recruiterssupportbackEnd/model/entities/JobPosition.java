
package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.HardSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.util.StringUtils;
/**
 *
 * @author Jhanuar Sanchez Rodriguez.12233507479
 * el cargo 
 */
public class JobPosition {
    
    @Id
    private String id;
    private String nit;
    private String name;
    private String description;
    private String salary;
    private List<Career> careers;
    private List<HardSkill> hardSkills;
    private List<SoftSkill> softSkills;
    private String vacants;//este es un  numerito (quedan 2 vacantes para ese puesto)

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Career> getCareers() {
        return careers;
    }

    public void setCareers(List<Career> careers) {
        this.careers = careers;
    }

    public List<HardSkill> getHardSkills() {
        return hardSkills;
    }

    public void setHardSkills(List<HardSkill> hardSkills) {
        this.hardSkills = hardSkills;
    }

    public List<SoftSkill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<SoftSkill> softSkills) {
        this.softSkills = softSkills;
    }

    public String getVacants() {
        return vacants;
    }

    public void setVacants(String vacants) {
        this.vacants = vacants;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addCareers(Career carrer) {
        this.careers.add(carrer);
    }
    
    public void addHardSkills(HardSkill recruiter) {
        this.hardSkills.add(recruiter);
    }
    
    public void addSoftSkills(SoftSkill recruiter) {
        this.softSkills.add(recruiter);
    }
    
    public void initCareers(){
        this.careers = new ArrayList<>();
    }
    
    public void initHardSkills(){
        this.hardSkills = new ArrayList<>();
    }
    
    public void initSoftSkills(){
        this.softSkills = new ArrayList<>();
    }
  
    public static boolean isCorrectForCreate(JobPosition jobposition) {
        return jobposition != null && !StringUtils.isEmpty(jobposition.getNit()) && !StringUtils.isEmpty(jobposition.getName()) && !StringUtils.isEmpty(jobposition.getSalary()) && !StringUtils.isEmpty(jobposition.getDescription()) && !StringUtils.isEmpty(jobposition.getVacants());
    }

    @Override
    public String toString() {
        return "JobPosition{" + "id=" + id + ", nit=" + nit + ", name=" + name + ", description=" + description + ", salary=" + salary + ", careers=" + careers + ", hardSkills=" + hardSkills + ", softSkills=" + softSkills + ", vacants=" + vacants + '}';
    }
    
    
}
