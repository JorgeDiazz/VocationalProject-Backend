package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity.TYPE;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public LoginController(PersonRepository personRepository, CompanyRepository companyRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
    }

    @PostMapping("/")
    public ResponseEntity<UserEntity> login(@RequestParam("user") String email, @RequestParam("password") String password) throws Throwable {

        Optional<Person> optPerson = personRepository.findByEmail(email);
        if (optPerson.isPresent()) {
            Person person = optPerson.get();
            if (person.getPassword().equals(password)) {

                HttpHeaders headers = new HttpHeaders();
                headers.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");

                return HttpResponseEntity.getOKStatus(person, headers);
            } else {
                throw new UnauthorizedException("incorrect credentials.");
            }
        }

        Optional<Company> optCompany = companyRepository.findByEmail(email);
        if (optCompany.isPresent()) {
            Company company = optCompany.get();
            if (company.getPassword().equals(password)) {
                company.setType(TYPE.COMPANY);

                HttpHeaders headers = new HttpHeaders();
                headers.add("token", "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9");

                return HttpResponseEntity.getOKStatus(company, headers);
            }
        }

        throw new UnauthorizedException("incorrect credentials.");
    }
}
