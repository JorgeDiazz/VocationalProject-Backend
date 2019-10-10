package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestChangeTypeSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.Company;
import com.recruiters.recruiterssupportbackEnd.model.entities.GlobalSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import java.util.List;
import java.util.Optional;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobSkill;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.GlobalSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.SkillRepository;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/Soft")
    public ResponseEntity<List<Skill>> getSoftSkills() {
        return HttpResponseEntity.getOKStatus(skillRepository.findAllSoft());
    }

    @GetMapping("/Hard")
    public ResponseEntity<List<Skill>> getHardSkills() {
        return HttpResponseEntity.getOKStatus(skillRepository.findAllHard());
    }

    @GetMapping("/GlobalByCompany/{nit}")
    public ResponseEntity<List<Skill>> getGlobalSkillsCompany(@PathVariable String nit) throws ExpectationFailedException {
        List<GlobalSkill> companyskills = globalSkillRepository.findGlobalCompany(nit);
        List<Skill> skillsbycompany = new ArrayList<>();

        if (!companyskills.isEmpty()) {
            for (GlobalSkill myskill : companyskills) {
                Optional<Skill> insert = skillRepository.findById(myskill.getIdSkill());
                skillsbycompany.add(insert.get());
            }
        } else {
            throw new ExpectationFailedException("there are no skills for this company");
        }
        return HttpResponseEntity.getOKStatus(skillsbycompany);
    }

    @GetMapping("/LocalByJobPosition/{id}")
    public ResponseEntity<List<Skill>> getJobSkillsCompany(@PathVariable int id) throws ExpectationFailedException {
        List<JobSkill> jobskills = jobSkillRepository.findbyidjob(id);
        List<Skill> skillsbyjob = new ArrayList<>();

        if (!jobskills.isEmpty()) {
            for (JobSkill myskill : jobskills) {
                Optional<Skill> insert = skillRepository.findById(myskill.getIdSkill());
                skillsbyjob.add(insert.get());
            }
        } else {
            throw new ExpectationFailedException("there are no skills for this job");
        }
        return HttpResponseEntity.getOKStatus(skillsbyjob);
    }

    @PostMapping("/register")
    public ResponseEntity<Skill> createSkill(@RequestBody Skill createRequestSkill) throws Throwable {
        String name = createRequestSkill.getName();

        Optional<Skill> optSkill = skillRepository.findByName(name); //Busqueda por name

        if (optSkill.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("skill already exist");
        } else {
            skillRepository.save(createRequestSkill);
            return HttpResponseEntity.getOKStatus(createRequestSkill);

        }
    }

    @PostMapping("/CreateGlobal")
    public ResponseEntity<GlobalSkill> createGlobalSkill(@RequestBody GlobalSkill createRequestSkill) throws Throwable {

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

    @PostMapping("/CreateLocal")
    public ResponseEntity<JobSkill> createLocalSkill(@RequestBody JobSkill createRequestSkill) throws Throwable {

        createRequestSkill.setId(String.valueOf(createRequestSkill.getIdJob()), String.valueOf(createRequestSkill.getIdSkill()));

        Optional<Skill> optSkill = skillRepository.findById(createRequestSkill.getIdSkill()); //Busqueda por ID

        if (optSkill.isPresent()) { // Si existe envia mensaje de Error
            throw new UnauthorizedException("skill doesn't exist");
        } else {

            Optional<JobPosition> optJob = jobPositionRepository.findById(createRequestSkill.getIdJob()); //Busqueda por ID
            if (optJob.isPresent()) { // Si existe envia mensaje de Error
                throw new UnauthorizedException("Job doesn't exist");
            } else {

                Optional<JobSkill> optLocalSkill = jobSkillRepository.findById(createRequestSkill.getId()); //Busqueda por ID
                if (optLocalSkill.isPresent()) { // Si existe envia mensaje de Error
                    throw new UnauthorizedException("skill already exist");
                } else {
                    jobSkillRepository.save(createRequestSkill);
                    return HttpResponseEntity.getOKStatus(createRequestSkill);
                }
            }
        }
    }

    @PutMapping("/")
    public ResponseEntity<Object> changeTypeSkill(@RequestBody CreateRequestChangeTypeSkill createRequestChangeTypeSkill) throws ExpectationFailedException {

        String nitCompany = createRequestChangeTypeSkill.getNitCompany();
        Optional<Company> company = companyRepository.findById(nitCompany);

        if (company.isPresent()) {

            int id = createRequestChangeTypeSkill.getId();
            Optional<Skill> skill = skillRepository.findById(createRequestChangeTypeSkill.getId());

            if (skill.isPresent()) {

                String newType = createRequestChangeTypeSkill.getNewType();

                if (newType.equalsIgnoreCase("global")) {

                    GlobalSkill globalSkill = new GlobalSkill();
                    globalSkill.setId(nitCompany, String.valueOf(id));
                    globalSkill.setNit(nitCompany);
                    globalSkill.setIdSkill(id);

                    try {
                        globalSkillRepository.save(globalSkill);
                    } catch (Exception e) {
                        throw new ExpectationFailedException("GlobalSkill data is incorrect");
                    }

                    return HttpResponseEntity.getOKStatus(globalSkill);

                } else {
                    if (newType.equalsIgnoreCase("specific")) {

                        try {
                            globalSkillRepository.deleteById(nitCompany + String.valueOf(id));
                        } catch (Exception e) {
                            throw new ExpectationFailedException("GlobalSkill doesn't exist");
                        }

                        return HttpResponseEntity.getOKStatus();

                    } else {
                        throw new ExpectationFailedException("newType is incorrect");
                    }
                }

            } else {
                throw new ExpectationFailedException("Skill doesn't exist");
            }

        } else {
            throw new ExpectationFailedException("Company doesn't exist");
        }
    }
}
