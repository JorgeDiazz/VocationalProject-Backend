package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JorgeDíaz
 */
public interface PostulantRepository extends JpaRepository<Postulant, String> {

    @Query("from Postulant where id = ?1")
    Optional<Postulant> findByEmail(String id);

}
