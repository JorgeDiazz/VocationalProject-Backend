package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import java.util.List;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.VacantRepository;
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

    private final MongoTemplate mongoTemplate;
    private final VacantRepository VacantRepository;

    @Autowired
    public VacantController(MongoTemplate mongoTemplate, VacantRepository vacantRepository) {
        this.mongoTemplate = mongoTemplate;
        this.VacantRepository = vacantRepository;
    }

    @GetMapping("/")
    public List<Vacant> getAllCompanies() {
        return VacantRepository.findAll();
    }

    @GetMapping("/{nit}")
    public ResponseEntity<Vacant> getVacant(@PathVariable String nit) throws Throwable {
        Query query = new Query(Criteria.where("nit").is(nit));
        Vacant vacant = mongoTemplate.findOne(query, Vacant.class);

        if (vacant == null) {
            return HttpResponseEntity.getNotFoundStatus();
        }

        return HttpResponseEntity.getOKStatus(vacant);
    }

    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@Valid @RequestBody Vacant vacant) {

        if (Vacant.isCorrectForCreate(vacant)) {
            vacant.initPostulants();
            VacantRepository.save(vacant);
            return HttpResponseEntity.getOKStatus(vacant);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }
}
