/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author seam33
 */
public interface CareerRepository extends JpaRepository<Career, Integer>{
    
    @Query("from Area where nit_company = ?1")
    List<Career> findByPerson (String nit);
    
    @Query("from Area where nit_company = ?1")
    List<Career> findByJob (String nit);
    
    @Query("from Career where name = ?1")
    Optional <Career> findByName (String name);
}
