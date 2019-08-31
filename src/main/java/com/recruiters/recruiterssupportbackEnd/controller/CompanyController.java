package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import java.util.List;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final MongoTemplate mongoTemplate;
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyController(MongoTemplate mongoTemplate, CompanyRepository companyRepository) {
        this.mongoTemplate = mongoTemplate;
        this.companyRepository = companyRepository;
    }

    @GetMapping("/")
    public List<Company> getAllComapanies() {
        return companyRepository.findAll();
    }

    @GetMapping("/{nit}")
    public ResponseEntity<Company> getCompany(@PathVariable String nit) throws Throwable {
        Query query = new Query(Criteria.where("nit").is(nit));
        Company company = mongoTemplate.findOne(query, Company.class);

        if (company == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }

        return HttpResponseEntity.getOKStatus(company);
    }

    @PostMapping("/")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {

        if (Company.isCorrectForCreate(company)) {
            companyRepository.save(company);
            return HttpResponseEntity.getOKStatus(company);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
