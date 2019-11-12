package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author jhanu
 */
public interface RecruiterVacantRepository extends JpaRepository<RecruiterVacant, Integer> {
    
    @Query("from RecruiterVacant where id = ?1")
    List<RecruiterVacant> findById(int id);
    
    @Query("from RecruiterVacant where id_person = ?1")
    List<RecruiterVacant> findByRecruiter(String id);
    
    @Query("from RecruiterVacant where id_vacant = ?1")
    List<RecruiterVacant> findByVacantId(int id);
    
    @Query(nativeQuery = true,value="select COUNT(*) AS numRecruiters FROM recruiter_vacant where id_vacant = ?1")
    int numRecuitersVacant(int id);
    
    @Query("from RecruiterVacant where id_person = ?1 and id_vacant= ?2")
    Optional<RecruiterVacant> findByidrecruvacant (String idreruiter,int idvacant);
    
}
