package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateVacantRequest;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.VacantRepository;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JorgeDíaz
 */
@RestController
@RequestMapping("/vacant")
public class VacantController {

    private final VacantRepository vacantRepository;

    @Autowired
    public VacantController(VacantRepository vacantRepository) {
        this.vacantRepository = vacantRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@RequestBody CreateVacantRequest newVacant) throws Throwable {

        Vacant vacant = new Vacant();
        vacant.setPlacesNumber(newVacant.getPlacesNumber());
        vacant.setNitJobPosition(newVacant.getIdJobPosition());

        Date date = new Date(System.currentTimeMillis());
        vacant.setStartDate(date);
        vacant.setEndDate(date);

        try {
            vacantRepository.save(vacant);
            return HttpResponseEntity.getOKStatus(vacant);

        } catch (Exception e) {
            throw new ExpectationFailedException("vacant data incorrect");
        }
    }
}
