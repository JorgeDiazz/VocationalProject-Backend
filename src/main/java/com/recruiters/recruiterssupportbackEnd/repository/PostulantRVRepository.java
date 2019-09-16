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
    
    @Query("from PostulantRV where id = ?1")
    List<PostulantRV> findById(int Id);

    @Query("from PostulantRV where state = ?1")
    List<PostulantRV> findByState(int id);
  
    @Query("from PostulantRV where idPerson = ?1")
    List<PostulantRV> findByPostulant(String id);

    @Query("from PostulantRV where idVacant = ?1")
    List<PostulantRV> findByRV(int id);

}
