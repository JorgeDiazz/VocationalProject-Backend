package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Area;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author seam33
 */

public interface AreaRepository extends JpaRepository<Area, Integer>{
    
    @Query("from Area where nit_company = ?1")
    List<Area> findByNit(String nit);
    
    @Query("from Area where nit_company = ?1 and name= ?2")
    Optional<Area> findByNitAndName (String nit,String name);
}
