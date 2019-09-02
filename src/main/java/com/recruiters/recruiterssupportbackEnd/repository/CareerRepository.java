/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *
 * @author seam33
 */
public interface CareerRepository extends MongoRepository<Career, String>{
    
}
