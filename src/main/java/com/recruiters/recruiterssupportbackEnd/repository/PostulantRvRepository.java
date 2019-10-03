package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Jhanuar
 */
public interface PostulantRvRepository extends JpaRepository<PostulantRv, Integer> {
    
    @Query("from PostulantRv where id = ?1")
    List<PostulantRv> findById(int Id);

    @Query("from PostulantRv where state = ?1")
    List<PostulantRv> findByState(int id);
  
    @Query("from PostulantRv where id_postulant = ?1")
    List<PostulantRv> findByPostulant(String id);

    @Query("from PostulantRv where id_rv = ?1")
    List<PostulantRv> findByRV(int id);

}
