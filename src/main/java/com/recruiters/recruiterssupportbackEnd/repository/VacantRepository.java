package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Jhanuar Sanchez
 */
public interface VacantRepository extends JpaRepository<Vacant, String>{
    
}

