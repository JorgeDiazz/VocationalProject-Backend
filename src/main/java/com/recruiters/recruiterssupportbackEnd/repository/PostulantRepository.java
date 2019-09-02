package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author seam33
 */

public interface PostulantRepository extends MongoRepository<Postulant, String>{
    
}
