package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author Jhanuar Sanchez
 */
public interface PersonRepository extends MongoRepository<Person, String>{
    
}

