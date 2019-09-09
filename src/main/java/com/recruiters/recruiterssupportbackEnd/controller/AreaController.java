/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.model.entities.Area;
import com.recruiters.recruiterssupportbackEnd.repository.AreaRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */

@RestController
@RequestMapping("/area")
public class AreaController {
    
    private final AreaRepository areaRepository;

    @Autowired
    public AreaController(AreaRepository areaRepository) {
        this.areaRepository = areaRepository;
    }
    
    @GetMapping("/{nit}")
    public List<Area> getAllAreaByNit(@PathVariable String nit){
        return areaRepository.findByNit(nit);
    }
}
