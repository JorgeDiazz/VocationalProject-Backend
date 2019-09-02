
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author seam33
 */

@RestController
@RequestMapping("/postulants")
public class PostulantController {
    
    private final PostulantRepository postulantRepository;

    @Autowired
    public PostulantController(PostulantRepository postulantRepository) {
        this.postulantRepository = postulantRepository;
    }
    
    @GetMapping("/")
    public List<Postulant> getAllPostulants() {
        return postulantRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<Postulant> createPostulant(@Valid @RequestBody Postulant postulant) {

        if(Postulant.isCorrectForCreate(postulant)){
            postulant.initCareer();
            postulant.initOnHoldVacantList();
            postulant.initInProcessVacantList(); 
            postulantRepository.save(postulant);
            return HttpResponseEntity.getOKStatus(postulant);   
        }
        return HttpResponseEntity.getMissingFieldsStatus();
    }
    
    
}
