package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestProcess;
import java.util.List;
import java.util.Optional;
import com.recruiters.recruiterssupportbackEnd.model.entities.Processs;
import com.recruiters.recruiterssupportbackEnd.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author katemorales
 */
@RestController
@RequestMapping("/process")
public class ProcessController {

    private final ProcessRepository processRepository;

    @Autowired
    public ProcessController(ProcessRepository processRepository) {
        this.processRepository = processRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})

    @GetMapping("/{nit}")
    public List<Processs> getProcessesByJob(@PathVariable int nit) {
        return processRepository.findByIdJob(nit);
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/register")
    public ResponseEntity<Processs> createRecruiter(@RequestBody CreateRequestProcess createRequestProcess) throws Throwable {

        int idJob = createRequestProcess.getId_job_position();
        String name = createRequestProcess.getName();

        Optional<Processs> optArea = processRepository.findByIdJobAndName(idJob, name); //Busqueda por ID

        if (optArea.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("process already exist");
        } else {

            Processs process = new Processs();
            process.setName(name);
            process.setId_job_position(idJob);
            processRepository.save(process);
            return HttpResponseEntity.getOKStatus(process);

        }
    }
}
