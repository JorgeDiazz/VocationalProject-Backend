package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import static com.recruiters.recruiterssupportbackEnd.controller.http.Constants.INCORRECT_CREDENTIALS;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.LoginRequest;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseCompanyLogin;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponsePostulantLogin;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseRecruiterLogin;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerP;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.repository.CareerPersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;
    private final CareerPersonRepository careerPersonRepository;

    @Autowired
    public LoginController(PersonRepository personRepository, CompanyRepository companyRepository, CareerPersonRepository careerPersonRepository) {
        this.personRepository = personRepository;
        this.companyRepository = companyRepository;
        this.careerPersonRepository = careerPersonRepository;
    }

    @PostMapping(name = "/")
    public ResponseEntity<UserEntity> login(@RequestBody LoginRequest loginRequest) throws Throwable {

        String user = loginRequest.getUser();
        String password = loginRequest.getPassword();
        try {
            Optional<Person> optPerson = personRepository.findByEmail(user);
            if (optPerson.isPresent()) {
                Person person = optPerson.get();
                if (person.getPassword().equals(password)) {
                    if (person.getType().equals("RECRUITER")) {
                        Optional<Company> optCompany = companyRepository.findByNit(person.getNitCompany());                  
                        CreateResponseRecruiterLogin sendreruiter = new CreateResponseRecruiterLogin(person.getId(), person.getName(), person.getEmail(),"http:cualquierurl.com", person.getNitCompany(), optCompany.get().getName(),person.getType());
                        return HttpResponseEntity.getOKStatus(sendreruiter, ResponseUtils.getJWTToken(person));
                    } else {
                        if (person.getType().equals("POSTULANT")) {
                            List<CareerP> listcarreerp = careerPersonRepository.findByIdPersonforlist(person.getId());
                            List ids = new ArrayList();
                            for (CareerP carrerp : listcarreerp) {
                                ids.add(carrerp.getIdCareer());
                            }
                            CreateResponsePostulantLogin sendpostulant = new CreateResponsePostulantLogin(person.getId(), person.getName(), person.getEmail(),"http:cualquierurl.com", ids,person.getType());
                            return HttpResponseEntity.getOKStatus(sendpostulant, ResponseUtils.getJWTToken(person));
                        } else {
                            throw new UnauthorizedException("algo raro paso");
                        }
                    }
                }
            } else {
                throw new UnauthorizedException(INCORRECT_CREDENTIALS);
            }
        } catch (Exception e) {
        }

        try {
            Optional<Company> optCompany = companyRepository.findByEmail(user);
            if (optCompany.isPresent()) {
                Company company = optCompany.get();
                if (company.getPassword().equals(password)) {
                    CreateResponseCompanyLogin sendcompany = new CreateResponseCompanyLogin(company.getNit(), company.getName(), company.getAddress(), company.getPhone(), "http:cualquierurl.com", company.getEmail(), "COMPANY");
                    return HttpResponseEntity.getOKStatus(sendcompany, ResponseUtils.getJWTToken(company));
                }
            } else {
            }
        } catch (Exception e) {
        }
        throw new UnauthorizedException("error imprevisto");
    }
}
