package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jhanuar Sanchez
 */
public interface VacantRepository extends MongoRepository<Vacant, String>{
    
}

