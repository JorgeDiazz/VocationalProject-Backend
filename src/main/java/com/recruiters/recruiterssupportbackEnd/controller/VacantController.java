package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import java.util.List;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
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
@RequestMapping("/vacants")
public class VacantController {

    private final JobPositionRepository jobPositionRepository;
    private final MongoTemplate mongoTemplate;

    @Autowired
    public VacantController(JobPositionRepository jobPositionRepository, MongoTemplate mongoTemplate) {
        this.jobPositionRepository = jobPositionRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @GetMapping("/{nit}")
    public ResponseEntity<List<Vacant>> getAllVacantsByJobPosition(@PathVariable String nit) throws Throwable{
        Query query = new Query(Criteria.where("nit").is(nit));
        JobPosition jobPosition = mongoTemplate.findOne(query, JobPosition.class);

        if (jobPosition == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }
        
       return HttpResponseEntity.getOKStatus(jobPosition.getVacants());
    }

    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@Valid @RequestBody Vacant vacant) {

        if (Vacant.isCorrectForCreate(vacant)) {
            Query query = new Query(Criteria.where("nit").is(vacant.getNitJobPosition()));
            JobPosition jobPositions = mongoTemplate.findOne(query, JobPosition.class);
            
            if (jobPositions == null) {
                return HttpResponseEntity.getNotFoundStatus();
            }
            
            jobPositions.getVacants().add(vacant);
            jobPositionRepository.save(jobPositions);

            return HttpResponseEntity.getOKStatus(vacant);
        }
        
        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
