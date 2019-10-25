package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.controller.response_entities.VacantByCareer;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JorgeDíaz
 */
public interface VacantRepository extends JpaRepository<Vacant, Integer> {

    @Query("from Vacant where id_job_position = ?1")
    List<Vacant> findByJobPositionId(int jobPositionId);
    
}
