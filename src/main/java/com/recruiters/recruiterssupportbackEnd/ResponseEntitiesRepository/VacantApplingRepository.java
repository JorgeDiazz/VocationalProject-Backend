/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.ResponseEntitiesRepository;

import com.recruiters.recruiterssupportbackEnd.controller.response_entities.VacantByCareer;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author seam33
 */
public interface VacantApplingRepository extends JpaRepository<VacantByCareer, Integer> {

    @Query(nativeQuery = true, value = "SELECT t3.id, t2.id_career, t1.name,t1.salary_min,t1.salary_max,t3.start_date,t3.places_number FROM job_position t1 JOIN career_job_position t2 ON t1.id=t2.id_job_position JOIN vacant t3 ON t1.id=t3.id_job_position JOIN jb_skill t4 ON t1.id=t4.id_job_position JOIN skill t5 ON t4.id_skill=t5.id JOIN recruiter_vacant t6 ON t6.id_vacant=t3.id where t2.id_career=?1 AND t5.class='Soft' GROUP BY t3.id")
    List<VacantByCareer> findAllVacantsApply(int id);

}

