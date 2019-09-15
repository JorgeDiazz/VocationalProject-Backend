package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJob;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author seam33
 */

public interface JobCareerRepository extends JpaRepository<CareerJob, String>{
    
}
