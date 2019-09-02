package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Recruiter;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import java.util.ArrayList;
import java.util.List;
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

    @GetMapping("/{nit}")
    public ResponseEntity<List<Recruiter>> getAllRecruitersByCompany(@PathVariable String nit) throws Throwable{
        Query query = new Query(Criteria.where("nit").is(nit));
        Company company = mongoTemplate.findOne(query, Company.class);

        if (company == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }
        
       return HttpResponseEntity.getOKStatus(company.getRecruiters());
    }

    @PostMapping("/")
    public ResponseEntity<Recruiter> createRecruiter(@Valid @RequestBody Recruiter recruiter) {

        if (Recruiter.isCorrectForCreate(recruiter)) {
             
            Query query = new Query(Criteria.where("nit").is(recruiter.getNitCompany()));
            Company company = mongoTemplate.findOne(query, Company.class);
            
            if (company == null) {
                return HttpResponseEntity.getNotFoundStatus();
            }
            
            recruiter.initPendingVacant();
            recruiter.initacceptedVacant();
            
            for (int i = 0; i < company.getJobsPositions().size(); i++) {
                
                List<Vacant> a = new ArrayList<>(company.getJobsPositions().get(i).getVacants());
                
                for (int j = 0; j < a.size(); j++) {
                    recruiter.addPendingVacant(a.get(j));
                }
            }
            
            company.addRecruiters(recruiter);
            companyRepository.save(company);

            return HttpResponseEntity.getOKStatus(recruiter);
        }
        
        return HttpResponseEntity.getMissingFieldsStatus();
    }

}
