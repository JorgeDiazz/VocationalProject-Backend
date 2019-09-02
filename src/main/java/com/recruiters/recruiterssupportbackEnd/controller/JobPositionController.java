package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
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
 * @author jhanuar Sanchez
 */
@RestController
@RequestMapping("/JobPosition")
public class JobPositionController {

    private final CompanyRepository companyRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public JobPositionController(CompanyRepository companyRepository, MongoTemplate mongoTemplate) {
        this.companyRepository = companyRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/{nit}")
    public ResponseEntity<List<JobPosition>> getAllJobpositionByCompany(@PathVariable String nit) throws Throwable{
        Query query = new Query(Criteria.where("nit").is(nit));
        Company company = mongoTemplate.findOne(query, Company.class);

        if (company == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }
        
       return HttpResponseEntity.getOKStatus(company.getJobsPositions());
    }

    @PostMapping("/")
    public ResponseEntity<JobPosition> createJobPosition(@Valid @RequestBody JobPosition jobposition) {

        if (JobPosition.isCorrectForCreate(jobposition)) {
            Query query = new Query(Criteria.where("nit").is(jobposition.getNitcompany()));
            Company company = mongoTemplate.findOne(query, Company.class);
            
            if (company == null) {
                return HttpResponseEntity.getNotFoundStatus();
            }
            
            jobposition.initCareers();
            jobposition.initHardSkills();
            jobposition.initSoftSkills();
            jobposition.initVacants();
            company.addJobsPositions(jobposition);
            companyRepository.save(company);

            return HttpResponseEntity.getOKStatus(jobposition);
        }
        
        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
