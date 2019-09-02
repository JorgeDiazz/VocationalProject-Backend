package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author jhanuar Sanchez
 */
@RestController
@RequestMapping("/vacants")
public class VacantController {

    private final CompanyRepository companyRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public VacantController(CompanyRepository companyRepository, MongoTemplate mongoTemplate) {
        this.companyRepository = companyRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@Valid @RequestBody Vacant vacant) {

        if (Vacant.isCorrectForCreate(vacant)) {
            Query query = new Query(Criteria.where("nit").is(vacant.getNitCompany()));
            Company company = mongoTemplate.findOne(query, Company.class);

            if (company == null) {
                return HttpResponseEntity.getNotFoundStatus();
            }
            
            
            for (int i = 0; i < company.getJobsPositions().size(); i++) {
                
                if(company.getJobsPositions().get(i).getName().equalsIgnoreCase(vacant.getNameJobPosition())){
                    
                    company.getJobsPositions().get(i).addVacants(vacant);
                    
                    for (int j = 0; j < company.getRecruiters().size(); j++) {
                        company.getRecruiters().get(j).addPendingVacant(vacant);
                    }
                    
                    companyRepository.save(company);
                    break;
                }
                
            }
            

            return HttpResponseEntity.getOKStatus(vacant);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
