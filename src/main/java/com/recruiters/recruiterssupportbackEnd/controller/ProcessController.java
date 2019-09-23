/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestProcess;
import java.util.List;
import java.util.Optional;
import com.recruiters.recruiterssupportbackEnd.model.entities.Process;
import com.recruiters.recruiterssupportbackEnd.repository.ProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @GetMapping("/{nit}")
    public List<Process> getProcessesByJob(@PathVariable int nit) {
        return processRepository.findByIdJob(nit);
    }

    @PostMapping("/register")
    public ResponseEntity<Process> createProcess(@RequestBody CreateRequestProcess createRequestProcess) throws Throwable {

        int idJob = createRequestProcess.getId_job_position();
        String name = createRequestProcess.getName();

        Optional<Process> optArea = processRepository.findByIdJobAndName(idJob, name); //Busqueda por ID

        if (optArea.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("process already exist");
        } else {

            Process process = new Process();
            process.setName(name);
            process.setId_job_position(idJob);
            processRepository.save(process);
            return HttpResponseEntity.getOKStatus(process);
        }
    }
}
