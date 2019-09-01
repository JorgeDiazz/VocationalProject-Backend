package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Recruiter;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */
@RestController
@RequestMapping("/recruiters")
public class RecruiterController {

    private final CompanyRepository companyRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public RecruiterController(CompanyRepository companyRepository, MongoTemplate mongoTemplate) {
        this.companyRepository = companyRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/")
    public List<Recruiter> getAllRecruiters() {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<Recruiter> createRecruiter(@Valid @RequestBody Recruiter recruiter) {

        if (Recruiter.isCorrectForCreate(recruiter)) {
             
            Query query = new Query(Criteria.where("nit").is(recruiter.getNitCompany()));
            Company company = mongoTemplate.findOne(query, Company.class);
            
            if (company == null) {
                return HttpResponseEntity.getNotFoundStatus();
            }
            
            company.addRecruiters(recruiter);
            companyRepository.save(company);

            return HttpResponseEntity.getOKStatus(recruiter);
        }
        
        return HttpResponseEntity.getMissingFieldsStatus();
    }

}
