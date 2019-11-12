/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import java.util.Optional;

/**
 *
 * @author katemorales, Jhoan Saavedra and Sebastian Aya
 */
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query("from Skill where name = ?1")
    Optional<Skill> findByName(String name);

    @Query(nativeQuery = true, value = "SELECT s1.* FROM skill s1 LEFT OUTER JOIN global_skill s2 ON CONCAT(?1,s1.id)=s2.id WHERE s2.id IS NULL AND s1.class='Soft';")
    List<Skill> findAllSoftByNit(String nit);

    @Query("from Skill where type='Soft'")
    List<Skill> findAllSoft();
    
    @Query("from Skill where type='Hard'")
    List<Skill> findAllHard();

    @Query(nativeQuery = true, value = "SELECT skill.* from skill JOIN global_skill ON skill.id=global_skill.id_skill WHERE global_skill.nit_company=?1")
    List<Skill> findAllGlobal(String nit);

    @Query("from Skill where id = ?1")
    Optional<Skill> findById(int id);
    
    @Query(value = "select count(*)  FROM jb_skill INNER JOIN skill ON jb_skill.id_skill=skill.id WHERE jb_skill.id_job_position=?1 AND skill.class='Soft'",nativeQuery = true)
    int CountSoftJobPosition(int idJobPosition);
    

}
