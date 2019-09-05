package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jhanuar Sanchez
 */
public interface JobPositionRepository extends JpaRepository<JobPosition, String>{
    
}
