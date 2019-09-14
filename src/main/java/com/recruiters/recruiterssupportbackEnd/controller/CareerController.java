/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestCareer;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestCareerJob;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.repository.CareerRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
@RequestMapping("/career")
public class CareerController {

    private final CareerRepository careerRepository;

    @Autowired
    public CareerController(CareerRepository careerRepository) {
        this.careerRepository = careerRepository;
    }
    
    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type","Authorization"})
    
    
    @GetMapping("/")
    public List<Career> getAllCareer(){
       return careerRepository.findAll();   
    }
    
     @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type","Authorization"})
    @PostMapping("/register")
    public ResponseEntity<UserEntity> createRecruiter(@RequestBody CreateRequestCareer createRequestCareer) throws Throwable {

        String name=createRequestCareer.getName();
        
        Optional<Career> optCareer = careerRepository.findByName(name); //Busqueda por ID

        if (optCareer.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("area already exist");
        } else {

                Career career = new Career();
                career.setName(name);
                careerRepository.save(career);
                return HttpResponseEntity.getOKStatus(career,ResponseUtils.generateTokenHeader((UserEntity) career));

        }
    }
    
    /*
    @PostMapping("/RegisterJob")
    public ResponseEntity<UserEntity> createRecruiter(@RequestBody CreateRequestCareerJob createRequestCareerJob) throws Throwable {

        String name=createRequestCareerJob.getName();
        
        Optional<Career> optCareer = careerRepository.findByName(name); //Busqueda por ID

        if (optCareer.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("area already exist");
        } else {

                Career career = new Career();
                career.setName(name);
                careerRepository.save(career);
                return HttpResponseEntity.getOKStatus(career,ResponseUtils.generateTokenHeader((UserEntity) career));

        }
    }
    
    @PostMapping("/RegisterPerson")
*/
    
}
