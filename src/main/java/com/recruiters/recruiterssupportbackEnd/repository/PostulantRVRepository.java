package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRV;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Jhanuar
 */
public interface PostulantRVRepository extends JpaRepository<PostulantRV, Integer> {
    /*
    @Query("from postulant_rv where id = ?1")
    List<PostulantRV> findById(int id);
    
    @Query("from postulant_rv where id = ?1")
    List<PostulantRV> findByState(int id);
    
    @Query("from postulant_rv where id_postulant = ?1")
    List<PostulantRV> findByPostulant(String id);

    @Query("from postulant_rv where id_rv = ?1")
    List<PostulantRV> findByRV(int id);
*/
}
