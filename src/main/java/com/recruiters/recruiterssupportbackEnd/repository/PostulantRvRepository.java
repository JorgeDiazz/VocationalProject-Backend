package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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

    @Query("from PostulantRv where id_postulant = ?1")
    Optional<PostulantRv> findByPostulant1(String id);
    
    @Query("from PostulantRv where id_rv = ?1")
    List<PostulantRv> findByRV(int id);
    

    @Modifying
    @Transactional
    @Query(nativeQuery = true,value = "insert into postulant_rv (state,id_postulant,id_vacant) VALUES (?1,?2,?3) ")
    void insert(int state,String idPostualnt,int idVacant);


}
