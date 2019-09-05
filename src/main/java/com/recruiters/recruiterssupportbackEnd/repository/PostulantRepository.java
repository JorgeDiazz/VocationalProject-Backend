package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author seam33
 */

public interface PostulantRepository extends JpaRepository<Postulant, String>{
    
}
