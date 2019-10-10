/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author katemorales
 */
public interface JobSkillRepository extends JpaRepository<JobSkill, String>{
    

    
    @Query("from JobSkill where id_skill = ?1")
    List<JobSkill> findbyidskill(int id);
    
    @Query("from JobSkill where id_job_position = ?1")
    List<JobSkill> findbyidjob(int id);
    
}
