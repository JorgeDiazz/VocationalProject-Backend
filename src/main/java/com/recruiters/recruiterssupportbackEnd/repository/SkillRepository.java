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
 * @author katemorales
 */
public interface SkillRepository extends JpaRepository<Skill, Integer> {

    @Query("from Skill where name = ?1")
    Optional<Skill> findByName(String name);

    @Query("from Skill where type='Soft'")
    List<Skill> findAllSoft();

    @Query("from Skill where type='Hard'")
    List<Skill> findAllHard();

    @Query(nativeQuery = true, value = "SELECT skill.* from skill INNER JOIN global_skill ON skill.id=global_skill.id_skill WHERE global_skill.nit_company=?1")
    List<Skill> findAllLocal(String nit);

    @Query("from Skill where name = ?1")
    Optional<Skill> findByid(int name);

}
