package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ConflictException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestRecruiter;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseRecruitersByCompany;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.UpdateRequestRecruiter;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{nit}")
    public ResponseEntity<List<Person>> getAllRecruiterByNit(@PathVariable String nit) {
        return HttpResponseEntity.getOKStatus(personRepository.findByNit(nit));
    }

    @PutMapping("/")
    public ResponseEntity<Person> updateRecruiter(@RequestBody UpdateRequestRecruiter updateRequestRecruiter) throws UnauthorizedException, ExpectationFailedException, ConflictException {

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
                    throw new ExpectationFailedException("email in use");
                } else {
                    newPerson.setEmail(email);
                }
            }

            if (password != null && !password.isEmpty()) {
                newPerson.setPassword(password);
            }

            try {
                personRepository.save(newPerson);
            } catch (Exception e) {
                throw new ExpectationFailedException("recruiter data is incorrect");
            }

            return HttpResponseEntity.getOKStatus(newPerson);

        } else {
            throw new ExpectationFailedException("recruiter doesn't exist");
        }

    }

    @PostMapping("/")//nit,id,email
    public ResponseEntity<UserEntity> createRecruiter(@RequestBody CreateRequestRecruiter createRequestRecruiter) throws Throwable {

        String id = createRequestRecruiter.getId();
        String email = createRequestRecruiter.getEmail();
        String nit = createRequestRecruiter.getNitCompany();

        if (personRepository.existsById(id)) { // Si existe envia mensaje de Error
            throw new ConflictException("user already exist");
        } else {

            if (personRepository.findByEmail(email).isPresent()) {
                throw new ExpectationFailedException("email in use");
            } else {
                Person recruiter = new Person();
                recruiter.setNitCompany(nit);
                recruiter.setId(id);
                recruiter.setName("USER " + id);
                recruiter.setEmail(email);
                recruiter.setPassword("12345");
                recruiter.setType(TYPE.RECRUITER.name());

                try {
                    personRepository.save(recruiter);
                } catch (Exception e) {
                    throw new ExpectationFailedException("recruiter data is incorrect");
                }

                return HttpResponseEntity.getOKStatus(recruiter);
            }
        }
    }

    @GetMapping("/recruiter/{nit}")
    public ResponseEntity<List<CreateResponseRecruitersByCompany>> getAllRecruiterByComanpy(@PathVariable String nit) throws ExpectationFailedException, UnauthorizedException, ConflictException {

        List<Person> reclutadores = personRepository.findByNit(nit);
        List<CreateResponseRecruitersByCompany> recluiterBycompanylist = new ArrayList<>();
        if (!reclutadores.isEmpty()) {
            

            for (Person recluiter : reclutadores) {
                int vacantcount = 0;
                int postulantcount = 0;
                // revisar si es una persona recluiter

                try {
                    String recluiterid = recluiter.getId();
                    List<RecruiterVacant> recluitervacantlist = recruiterVacantRepository.findByRecruiter(recluiterid);
                    for (RecruiterVacant rv : recluitervacantlist) {
                        vacantcount++;
                        int recruitervacantid = rv.getId();
                        List<PostulantRv> postulantlist = postulantRvRepository.findByRV(recruitervacantid);
                        for (PostulantRv prv : postulantlist) {
                            postulantcount++;
                        }
                    }
                } catch (Exception e) {
                    //throw new ConflictException("company dosen't have recluiters");
                    return HttpResponseEntity.getOKStatus(recluiterBycompanylist);
                }

                CreateResponseRecruitersByCompany newrecru = new CreateResponseRecruitersByCompany(recluiter.getId(), recluiter.getName(), vacantcount, postulantcount);
                recluiterBycompanylist.add(newrecru);

            }
            return HttpResponseEntity.getOKStatus(recluiterBycompanylist);
        } else {
           // throw new ConflictException("there are no recruiters in the database");
           return HttpResponseEntity.getOKStatus(recluiterBycompanylist);
        }

    }
}
