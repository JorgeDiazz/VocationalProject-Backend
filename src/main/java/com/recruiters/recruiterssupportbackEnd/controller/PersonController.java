/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity.TYPE;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    private final PersonRepository personRepository;

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @PostMapping("/registerR")
    public ResponseEntity<UserEntity> createRecruiter(@RequestParam("nit") String nit, @RequestParam("id") String id, @RequestParam("email") String email) throws Throwable {

        Optional<Person> optPerson = personRepository.findById(id); //Busqueda por ID

        if (optPerson.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("user already exist");
        } else {  

            Optional<Person> optPerson2 = personRepository.findByEmail(email); //Busqueda por correo - No pueden existir 2 personas con el mismo correo

            if (optPerson2.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("email in use");
            } else {
                Person recruiter = new Person();
                recruiter.setNitCompany(nit);
                recruiter.setId(id);
                recruiter.setName("USER " + id);
                recruiter.setEmail(email);
                recruiter.setPassword("12345");
                recruiter.setType(TYPE.RECRUITER.name());
                personRepository.save(recruiter);
                return HttpResponseEntity.getOKStatus(recruiter, ResponseUtils.generateTokenHeader(recruiter));

            }
        }
    }
}
