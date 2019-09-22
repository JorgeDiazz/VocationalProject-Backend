/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestArea;
import com.recruiters.recruiterssupportbackEnd.model.entities.Area;
import com.recruiters.recruiterssupportbackEnd.repository.AreaRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/{nit}")
    public ResponseEntity<List<Area>> getAllAreaByNit(@PathVariable String nit) {
        return HttpResponseEntity.getOKStatus(areaRepository.findByNit(nit));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/register")
    public ResponseEntity<Area> createArea(@RequestBody CreateRequestArea createRequestArea) throws Throwable {

        String nitCompany = createRequestArea.getNitCompany();
        String name = createRequestArea.getName();

        Optional<Area> optArea = areaRepository.findByNitAndName(nitCompany, name); //Busqueda por ID

        if (optArea.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("area already exist");
        } else {

            Area area = new Area();
            area.setName(name);
            area.setNit_company(nitCompany);
            areaRepository.save(area);
            return HttpResponseEntity.getOKStatus(area);
        }

    }
}
