/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author seam33
 */
public interface RecruiterRepository extends JpaRepository<Recruiter,String>{
    
}
