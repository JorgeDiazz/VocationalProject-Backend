/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.GlobalSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author katemorales
 */
public interface GlobalSkillRepository extends JpaRepository<GlobalSkill, String>{

    @Query("from Skill where name = ?1")
    List<Skill> findGlobalCompany(String nit);
}
