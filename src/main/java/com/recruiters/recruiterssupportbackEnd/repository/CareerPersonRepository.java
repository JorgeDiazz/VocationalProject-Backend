/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.CareerP;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CareerPersonRepository extends JpaRepository<CareerP, Integer> {

    @Query("from careerp where id_person = ?1")
    List<CareerP> findByIdPerson(String idperson);
 
}
