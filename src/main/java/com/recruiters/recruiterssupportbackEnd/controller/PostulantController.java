package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import org.springframework.util.StringUtils;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreatePostulantRequest;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author JorgeDíaz
 */
@RestController
@RequestMapping("/postulant")
public class PostulantController {

    private final PersonRepository personRepository;
    private final PostulantRepository postulantRepository;

    @Autowired
    public PostulantController(PersonRepository personRepository, PostulantRepository postulantRepository) {
        this.personRepository = personRepository;
        this.postulantRepository = postulantRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/")
    public ResponseEntity<UserEntity> createPostulant(@RequestBody CreatePostulantRequest newPostulant) throws Throwable {

        String id = newPostulant.getId();
        String email = newPostulant.getEmail();

        Optional<Person> optPerson = personRepository.findById(id);

        if (optPerson.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("user already exist");
        } else {

            Optional<Person> optPerson2 = personRepository.findByEmail(email); //Busqueda por correo - No pueden existir 2 personas con el mismo correo

            if (optPerson2.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("email in use");
            } else {
                Postulant postulant = new Postulant();
                postulant.setType(UserEntity.TYPE.POSTULANT.name());
                postulant.setIdPerson(newPostulant.getId());

                Person person = new Person();
                person.setId(id);
                person.setName(newPostulant.getName());
                person.setEmail(email);
                person.setPassword(newPostulant.getPassword());
                person.setType(UserEntity.TYPE.POSTULANT.name());

                try {
                    personRepository.save(person);
                    postulantRepository.save(postulant);
                } catch (Exception e) {
                    throw new ExpectationFailedException("person/postulant data is incorrect");
                }

                return HttpResponseEntity.getOKStatus(person);
            }
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PutMapping("/")
    public ResponseEntity<UserEntity> updatePostulant(@RequestBody CreatePostulantRequest newPostulant) throws Throwable {

        String id = newPostulant.getId();

        Optional<Person> optPerson = personRepository.findById(id);

        if (!optPerson.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("user doesn't exist");
        } else {

            Person person = optPerson.get();
            Postulant postulant = new Postulant();

            postulant.setType(UserEntity.TYPE.POSTULANT.name());
            postulant.setIdPerson(newPostulant.getId());

            person.setId(id);
            person.setName(StringUtils.isEmpty(newPostulant.getName()) ? person.getName() : newPostulant.getName());
            person.setEmail(StringUtils.isEmpty(newPostulant.getEmail()) ? person.getEmail() : newPostulant.getEmail());
            person.setPassword(StringUtils.isEmpty(newPostulant.getPassword()) ? person.getPassword() : newPostulant.getPassword());
            person.setType(UserEntity.TYPE.POSTULANT.name());

            try {
                personRepository.save(person);
                postulantRepository.save(postulant);
            } catch (Exception e) {
                throw new ExpectationFailedException("person/postulant data is incorrect");
            }

            return HttpResponseEntity.getOKStatus(person);
        }
    }

}
