package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import java.util.List;
import javax.validation.Valid;
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
 * @author jhanuar Sanchez
 */
@RestController
@RequestMapping("/JobPosition")
public class JobPositionController {

    private final CompanyRepository companyRepository;

    @Autowired
    public JobPositionController(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @GetMapping("/{nit}")
    public ResponseEntity<List<JobPosition>> getAllJobpositionByCompany(@PathVariable String nit) throws Throwable {
        return null;
    }

    @PostMapping("/")
    public ResponseEntity<JobPosition> createJobPosition(@Valid @RequestBody JobPosition jobposition) {
        return null;
    }
}
