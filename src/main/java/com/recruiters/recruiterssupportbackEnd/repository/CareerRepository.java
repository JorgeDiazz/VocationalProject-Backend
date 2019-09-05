package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author seam33
 */
public interface CareerRepository extends JpaRepository<Career, String>{
    
}
