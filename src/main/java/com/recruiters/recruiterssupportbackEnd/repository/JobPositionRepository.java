package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author seam33
 */
public interface JobPositionRepository extends MongoRepository<Company, String>{
    
}
