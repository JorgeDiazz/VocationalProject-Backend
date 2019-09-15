package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJobPosition;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author JorgeDíaz
 */
public interface CareerJobPositionRepository extends JpaRepository<CareerJobPosition, Integer> {

    @Query("from CareerJobPosition where id_career = ?1")
    Optional<CareerJobPosition> findByCareerId(int idCareer);
}
