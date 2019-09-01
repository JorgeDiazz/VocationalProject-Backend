package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
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

    private final MongoTemplate mongoTemplate;
    private final JobPositionRepository jobPositionRepository;

    @Autowired
    public JobPositionController(MongoTemplate mongoTemplate, JobPositionRepository jobPositionRepository) {
        this.mongoTemplate = mongoTemplate;
        this.jobPositionRepository = jobPositionRepository;
    }

    @GetMapping("/")
    public List<JobPosition> getAllJobPositions() {
        return jobPositionRepository.findAll();
    }

    @GetMapping("/{nit}")
    public ResponseEntity<JobPosition> getJobPosition(@PathVariable String nit) throws Throwable {
        Query query = new Query(Criteria.where("nit").is(nit));
        JobPosition jobPosition = mongoTemplate.findOne(query, JobPosition.class);

        if (jobPosition == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }

        return HttpResponseEntity.getOKStatus(jobPosition);
    }

    @PostMapping("/")
    public ResponseEntity<JobPosition> createJobPosition(@Valid @RequestBody JobPosition jobPosition) {

        if (JobPosition.isCorrectForCreate(jobPosition)) {
            jobPosition.initCareers();
            jobPosition.initHardSkills();
            jobPosition.initSoftSkills();
            jobPositionRepository.save(jobPosition);
            return HttpResponseEntity.getOKStatus(jobPosition);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
