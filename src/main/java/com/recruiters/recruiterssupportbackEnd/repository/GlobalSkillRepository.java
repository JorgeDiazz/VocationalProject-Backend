/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.GlobalSkill;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author katemorales
 */
public interface GlobalSkillRepository extends JpaRepository<GlobalSkill, String>{

    @Query("from GlobalSkill where nit_company = ?1")
    List<GlobalSkill> findGlobalCompany(String nit);
}
