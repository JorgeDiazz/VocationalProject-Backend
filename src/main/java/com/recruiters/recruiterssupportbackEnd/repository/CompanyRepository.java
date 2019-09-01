package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CompanyRepository extends MongoRepository<Company, String>{
    
}
