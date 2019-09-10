/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.repository.CareerRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */

@RestController
@RequestMapping("/career")
public class CareerController {

    private final CareerRepository careerRepository;

    @Autowired
    public CareerController(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }
    
    @GetMapping("/")
    public List<Career> getAllCareer(){
       return careerRepository.findAll();   
    }
   
    
}
