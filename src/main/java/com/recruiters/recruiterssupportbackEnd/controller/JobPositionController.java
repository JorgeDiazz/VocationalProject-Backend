package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33 jhanu
 */
@RestController
@RequestMapping("/jobposition")
public class JobPositionController {

    private final JobPositionRepository jobPositionRepository;

    @Autowired
    public JobPositionController(JobPositionRepository jobPositionRepository) {
        this.jobPositionRepository = jobPositionRepository;
    }

    /*
     @PostMapping("/RegisterJob")
     public ResponseEntity<UserEntity> createRecruiter(@RequestBody CreateRequestCareerJob createRequestCareerJob, @RequestHeader(value = "Authorization") String token) throws Throwable {
    
     String name=createRequestCareerJob.getName();
        
     Optional<Career> optCareer = careerRepository.findByName(name); //Busqueda por ID

     if (optCareer.isPresent()) { // Si existe envia mensaje de Error
     throw new UnauthorizedException("area already exist");
     } else {

     Career career = new Career();
     career.setName(name);
     careerRepository.save(career);
     return HttpResponseEntity.getOKStatus(career,ResponseUtils.generateTokenHeader((UserEntity) career));

     }
     }
    
     @PostMapping("/RegisterPerson")
     */
}
