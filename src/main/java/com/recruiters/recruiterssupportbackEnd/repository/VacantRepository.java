package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import java.util.Date;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JorgeDíaz
 */
public interface VacantRepository extends JpaRepository<Vacant, Integer> {

   @Query("from Vacant where places_number=?1 and start_date=?2 and id_job_position=?3")
   Optional<Vacant> findByDateJobNum(int placesNumber, Date date, int idJob); 
    
}
