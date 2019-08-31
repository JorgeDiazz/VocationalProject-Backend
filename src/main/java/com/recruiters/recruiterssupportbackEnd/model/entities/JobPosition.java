
package com.recruiters.recruiterssupportbackEnd.model.entities;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.HardSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import java.util.List;
import org.springframework.data.annotation.Id;
/**
 *
 * @author Jhanuar Sanchez Rodriguez.12233507479
 * el cargo 
 */
public class JobPosition {
    
    @Id
    private String id;
    private String nit;
    private String salary;
    private String Salario;
    private String description;
    private List<Career> careers;
    private List<HardSkill> hardskills;
    private List<SoftSkill> softSkills;
    private String vacants;//este es un  numerito (quedan 2 vacantes para ese puesto)

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getSalario() {
        return Salario;
    }

    public void setSalario(String Salario) {
        this.Salario = Salario;
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

    public List<HardSkill> getHardskills() {
        return hardskills;
    }

    public void setHardskills(List<HardSkill> hardskills) {
        this.hardskills = hardskills;
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
    
    
}
