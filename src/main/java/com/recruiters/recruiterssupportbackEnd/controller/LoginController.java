package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import static com.recruiters.recruiterssupportbackEnd.controller.http.Constants.INCORRECT_CREDENTIALS;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity.TYPE;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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
                return HttpResponseEntity.getOKStatus(person, ResponseUtils.generateTokenHeader(person));
            } else {
                throw new UnauthorizedException(INCORRECT_CREDENTIALS);
            }
        }

        Optional<Company> optCompany = companyRepository.findByEmail(email);
        if (optCompany.isPresent()) {
            Company company = optCompany.get();
            if (company.getPassword().equals(password)) {
                company.setType(TYPE.COMPANY);
                return HttpResponseEntity.getOKStatus(company, ResponseUtils.generateTokenHeader(company));
            }
        }

        throw new UnauthorizedException(INCORRECT_CREDENTIALS);
    }
}
