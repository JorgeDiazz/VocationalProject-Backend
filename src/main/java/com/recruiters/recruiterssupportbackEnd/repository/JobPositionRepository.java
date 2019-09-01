package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jhanuar Sanchez
 */
public interface JobPositionRepository extends MongoRepository<JobPosition, String>{
    
}
