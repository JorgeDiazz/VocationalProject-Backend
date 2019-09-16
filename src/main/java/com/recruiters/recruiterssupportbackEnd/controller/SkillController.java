/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.http.ResponseUtils;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.GlobalSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import java.util.List;
import java.util.Optional;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.SkillJob;
import com.recruiters.recruiterssupportbackEnd.model.entities.UserEntity;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.GlobalSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author katemorales
 */
@RestController
@RequestMapping("/skill")
public class SkillController {

    private final SkillRepository skillRepository;
    private final CompanyRepository companyRepository;
    private final GlobalSkillRepository globalSkillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final JobPositionRepository jobPositionRepository;

    @Autowired
    public SkillController(SkillRepository skillRepository, CompanyRepository companyRepository, GlobalSkillRepository globalSkillRepository, JobSkillRepository jobSkillRepository, JobPositionRepository jobPositionRepository) {
        this.skillRepository = skillRepository;
        this.companyRepository = companyRepository;
        this.globalSkillRepository = globalSkillRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.jobPositionRepository = jobPositionRepository;

    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/Soft")
    public List<Skill> getSoftSkills(@RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {
            return skillRepository.findAllSoft();
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/Hard")
    public List<Skill> getHardSkills(@RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            return skillRepository.findAllHard();
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/GlobalByCompany/{nit}")
    public List<Skill> getGlobalSkillsCompany(@PathVariable String nit, @RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {
            return globalSkillRepository.findGlobalCompany(nit);
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/LocalByJobPosition/{id}")
    public List<Skill> getJobSkillsCompany(@PathVariable int id, @RequestHeader(value = "Authorization") String token) throws UnauthorizedException {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            return jobSkillRepository.findLocalJob(id);
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/register")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill createRequestSkill, @RequestHeader(value = "Authorization") String token) throws Throwable {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            String name = createRequestSkill.getName();

            Optional<Skill> optSkill = skillRepository.findByName(name); //Busqueda por name

            if (optSkill.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("skill already exist");
            } else {
                skillRepository.save(createRequestSkill);
                return HttpResponseEntity.getOKStatus(createRequestSkill);

            }
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/CreateGlobal")
    public ResponseEntity<GlobalSkill> createGlobalSkill(@RequestBody GlobalSkill createRequestSkill, @RequestHeader(value = "Authorization") String token) throws Throwable {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            createRequestSkill.setId(createRequestSkill.getNit(), String.valueOf(createRequestSkill.getIdSkill()));

            Optional<Skill> optSkill = skillRepository.findById(createRequestSkill.getIdSkill()); //Busqueda por ID

            if (optSkill.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("skill doesn't exist");
            } else {

                Optional<Company> optCompany = companyRepository.findById(createRequestSkill.getNit()); //Busqueda por ID
                if (optCompany.isPresent()) { // Si existe envia mensaje de Error
                    throw new UnauthorizedException("Company doesn't exist");
                } else {

                    Optional<GlobalSkill> optGlobalSkill = globalSkillRepository.findById(createRequestSkill.getId()); //Busqueda por ID
                    if (optGlobalSkill.isPresent()) { // Si existe envia mensaje de Error
                        throw new UnauthorizedException("skill already exist");
                    } else {
                        globalSkillRepository.save(createRequestSkill);
                        return HttpResponseEntity.getOKStatus(createRequestSkill);
                    }

                }

            }
        }
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST, RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/CreateLocal")
    public ResponseEntity<SkillJob> createLocalSkill(@RequestBody SkillJob createRequestSkill, @RequestHeader(value = "Authorization") String token) throws Throwable {
        if (Integer.parseInt(ResponseUtils.Validation(token).get(0)) != 1 /*&& ResponseUtils.Validation(token).get(1)== "COMPANY"*/) {//1 para no se vencio   
            throw new UnauthorizedException("Validation Problem");
        } else {

            createRequestSkill.setId(String.valueOf(createRequestSkill.getIdJob()), String.valueOf(createRequestSkill.getIdSkill()));

            Optional<Skill> optSkill = skillRepository.findById(createRequestSkill.getIdSkill()); //Busqueda por ID

            if (optSkill.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("skill doesn't exist");
            } else {

                Optional<JobPosition> optJob = jobPositionRepository.findById(createRequestSkill.getIdJob()); //Busqueda por ID
                if (optJob.isPresent()) { // Si existe envia mensaje de Error
                    throw new UnauthorizedException("Job doesn't exist");
                } else {

                    Optional<SkillJob> optLocalSkill = jobSkillRepository.findById(createRequestSkill.getId()); //Busqueda por ID
                    if (optLocalSkill.isPresent()) { // Si existe envia mensaje de Error
                        throw new UnauthorizedException("skill already exist");
                    } else {
                        jobSkillRepository.save(createRequestSkill);
                        return HttpResponseEntity.getOKStatus(createRequestSkill);
                    }

                }

            }
        }
    }
}
