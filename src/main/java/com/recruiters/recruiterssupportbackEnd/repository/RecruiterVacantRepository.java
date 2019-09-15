package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jhanu
 */
public interface RecruiterVacantRepository extends JpaRepository<RecruiterVacant, Integer> {
    /*
    @Query("from recruiter_vacant where id = ?1")
    List<RecruiterVacant> findById(int id);
    
    @Query("from recruiter_vacant where id_person = ?1")
    List<RecruiterVacant> findByRecruiter(String id);
    
    @Query("from recruiter_vacant where id_vacant = ?1")
    List<RecruiterVacant> findByvacant(int id);
    */
}
