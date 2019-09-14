/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;

/**
 *
 * @author katemorales
 */
public interface SkillRepository extends JpaRepository<Skill, Integer>{
    
    @Query("from Skill where id_job_position = ?1")
    List<Skill> findByIdJob(String idJobPosition);
}
