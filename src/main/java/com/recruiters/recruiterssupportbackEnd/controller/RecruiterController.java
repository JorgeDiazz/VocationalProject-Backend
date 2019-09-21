package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestRecruiter;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseRecluitersByCompany;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.UpdateRequestRecruiter;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity.TYPE;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRvRepository;
import com.recruiters.recruiterssupportbackEnd.repository.RecruiterVacantRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */
@RestController
@RequestMapping("/recruiter")
public class RecruiterController {

    private final PersonRepository personRepository;
    private final RecruiterVacantRepository recruiterVacantRepository;
    private final PostulantRvRepository postulantRvRepository;

    @Autowired
    public RecruiterController(PersonRepository personRepository, RecruiterVacantRepository recruiterVacantRepository, PostulantRvRepository postulantRVRepository) {
        this.personRepository = personRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
        this.postulantRvRepository = postulantRVRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/{nit}")
    public ResponseEntity<List<Person>> getAllRecruiterByNit(@PathVariable String nit, @RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {
            return HttpResponseEntity.getOKStatus(personRepository.findByNit(nit));
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.PUT}, allowedHeaders = {"Content-Type", "Authorization"})
    @PutMapping("/")
    public ResponseEntity<Person> updateRecruiter(@RequestBody UpdateRequestRecruiter updateRequestRecruiter, @RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            String id = updateRequestRecruiter.getId();
            String name = updateRequestRecruiter.getName();
            String email = updateRequestRecruiter.getEmail();
            String password = updateRequestRecruiter.getPassword();

            Optional<Person> optPerson = personRepository.findById(id);

            if (optPerson.isPresent()) {

                Person newPerson = optPerson.get();

                if (name != null && !name.isEmpty()) {
                    newPerson.setName(name);
                }

                if (email != null && !email.isEmpty()) {

                    if (personRepository.findByEmail(email).isPresent()) {
                        throw new UnauthorizedException("email in use");
                    } else {
                        newPerson.setEmail(email);
                    }
                }

                if (password != null && !password.isEmpty()) {
                    newPerson.setPassword(password);
                }

                personRepository.save(newPerson);

                return HttpResponseEntity.getOKStatus(newPerson, ResponseUtils.generateTokenHeader(newPerson));

            }
        }
        throw new UnauthorizedException("could not update");
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/")//nit,id,email
    public ResponseEntity<UserEntity> createRecruiter(@RequestBody CreateRequestRecruiter createRequestRecruiter, @RequestHeader(value = "Authorization") String token) throws Throwable {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            String id = createRequestRecruiter.getId();
            String email = createRequestRecruiter.getEmail();
            String nit = createRequestRecruiter.getNit();

            if (personRepository.existsById(id)) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("user already exist");
            } else {

                if (personRepository.findByEmail(email).isPresent()) {
                    throw new UnauthorizedException("email in use");
                } else {
                    Person recruiter = new Person();
                    recruiter.setNitCompany(nit);
                    recruiter.setId(id);
                    recruiter.setName("USER " + id);
                    recruiter.setEmail(email);
                    recruiter.setPassword("12345");
                    recruiter.setType(TYPE.RECRUITER.name());
                    personRepository.save(recruiter);
                    return HttpResponseEntity.getOKStatus(recruiter);
                }
            }

        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/manager/{nit}")
    public ResponseEntity<List<CreateResponseRecluitersByCompany>> getAllRecruiterByComany(@PathVariable String nit, @RequestHeader(value = "Authorization") String token) throws ExpectationFailedException, UnauthorizedException {

        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            List<Person> reclutadores = personRepository.findByNit(nit);
            System.out.println("lista de reclutadores:   " + reclutadores.toString());
            if (!reclutadores.isEmpty()) {
                List<CreateResponseRecluitersByCompany> recluiterBycompanylist = new ArrayList<>();

                for (Person recluiter : reclutadores) {
                    int vacantcount = 0;
                    int postulantcount = 0;
                    // revisar si es una persona recluiter

                    try {
                        String recluiterid = recluiter.getId();
                        List<RecruiterVacant> recluitervacantlist = recruiterVacantRepository.findByRecruiter(recluiterid);
                        System.out.println("lista de vacantes:   " + recluitervacantlist.toString());
                        for (RecruiterVacant rv : recluitervacantlist) {
                            vacantcount++;
                            int recruitervacantid = rv.getId();
                            List<PostulantRv> postulantlist = postulantRvRepository.findByRV(recruitervacantid);
                            System.out.println("lista de postulantes:   " + postulantlist.toString());
                            for (PostulantRv prv : postulantlist) {
                                postulantcount++;
                            }
                        }
                    } catch (Exception e) {
                    }

                    CreateResponseRecluitersByCompany newrecru = new CreateResponseRecluitersByCompany(recluiter.getId(), recluiter.getName(), vacantcount, postulantcount);
                    recluiterBycompanylist.add(newrecru);

                }
                return HttpResponseEntity.getOKStatus(recluiterBycompanylist);
            } else {
                throw new ExpectationFailedException("there are no recruiters in the database");
            }

        }
    }
}
