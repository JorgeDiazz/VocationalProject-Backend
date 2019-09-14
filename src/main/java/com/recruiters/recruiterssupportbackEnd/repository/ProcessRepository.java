/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.recruiters.recruiterssupportbackEnd.model.entities.Process;
import java.util.Optional;

/**
 *
 * @author katemorales
 */
public interface ProcessRepository extends JpaRepository<Process, Integer> {
    
    @Query("from Process where id_job_position = ?1")
    List<Process> findByIdJob(int idJobPosition);
    
    @Query("from Process where id_job_position = ?1 and name")
    Optional <Process> findByIdJobAndName(int idJobPosition,String name);
}
