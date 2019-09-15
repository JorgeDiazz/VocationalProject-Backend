package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateVacantRequest;
import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.RecruiterVacantRepository;
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
    private final RecruiterVacantRepository recruiterVacantRepository;

    @Autowired
    public VacantController(VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository) {
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@RequestBody CreateVacantRequest newVacant) throws Throwable {

        Vacant vacant = new Vacant();
        vacant.setPlacesNumber(newVacant.getPlacesNumber());
        vacant.setNitJobPosition(newVacant.getIdJobPosition());

        Date date = new Date(System.currentTimeMillis());
        vacant.setStartDate(date);

        try {
            vacantRepository.save(vacant);
        } catch (Exception e) {
            throw new ExpectationFailedException("Vacant data is incorrect");
        }
        try {
            RecruiterVacant recruiterVacant;
            for (String idRecruiter : newVacant.getPostulantsId()) {
                recruiterVacant = new RecruiterVacant();
                recruiterVacant.setIdPerson(idRecruiter);
                recruiterVacant.setIdVacant(vacant.getId());
                recruiterVacantRepository.save(recruiterVacant);
            }
        } catch (Exception e) {
            throw new ExpectationFailedException("Recruiter doesn't exist or recruiter-vacant already on database");
        }

        return HttpResponseEntity.getOKStatus(vacant);
    }
}
