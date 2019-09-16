package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.UnauthorizedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJob;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.Processs;
import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.SkillJob;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.CareerRepository;
import com.recruiters.recruiterssupportbackEnd.repository.CompanyRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobCareerRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.ProcessRepository;
import com.recruiters.recruiterssupportbackEnd.repository.RecruiterVacantRepository;
import com.recruiters.recruiterssupportbackEnd.repository.SkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.VacantRepository;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.util.Strings;
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
 * @author seam33
 */
@RestController
@RequestMapping("/jobPosition")

public class JobPositionController {

    private final CompanyRepository companyRepository;
    private final JobPositionRepository jobPositionRepository;
    private final CareerRepository careerRepository;
    private final SkillRepository skillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final JobCareerRepository jobCareerRepository;
    private final ProcessRepository processRepository;
    private final VacantRepository vacantRepository;
    private final RecruiterVacantRepository recruiterVacantRepository;

    @Autowired
    public JobPositionController(CompanyRepository companyRepository, JobPositionRepository jobPositionRepository, CareerRepository careerRepository, SkillRepository skillRepository, JobSkillRepository jobSkillRepository, ProcessRepository processRepository, JobCareerRepository jobCareerRepository, VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository) {
        this.companyRepository = companyRepository;
        this.jobPositionRepository = jobPositionRepository;
        this.careerRepository = careerRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.processRepository = processRepository;
        this.jobCareerRepository = jobCareerRepository;
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.GET}, allowedHeaders = {"Content-Type", "Authorization"})
    @GetMapping("/{nit}")
    public ResponseEntity<List<JobPosition>> getAllJobPosition(@PathVariable String nit) {
        return HttpResponseEntity.getOKStatus(jobPositionRepository.findJobs(nit));
    }

    @CrossOrigin(origins = "*", methods = {RequestMethod.POST}, allowedHeaders = {"Content-Type", "Authorization"})
    @PostMapping("/")
    public void createJobPosition(@RequestBody CreateRequestJobPosition createRequestJobPosition) throws UnauthorizedException, ExpectationFailedException {

        String nitCompany = createRequestJobPosition.getNitCompany();

        if (nitCompany != null && companyRepository.existsById(nitCompany)) {

            String name = createRequestJobPosition.getName();
            double salaryMin = createRequestJobPosition.getSalaryMin();
            double salaryMax = createRequestJobPosition.getSalaryMax();
            int idArea = createRequestJobPosition.getIdArea();
            String description = createRequestJobPosition.getDescription();
            List<Career> career = createRequestJobPosition.getCareer();
            List<Career> newCareer = createRequestJobPosition.getNewCareer();
            List<Skill> hardSkill = createRequestJobPosition.getHardSkill();
            List<Skill> newHardSkill = createRequestJobPosition.getNewHardSkill();
            List<Processs> processs = createRequestJobPosition.getProcess();
            List<Person> recruiter = createRequestJobPosition.getRecruiter();
            int placesNumber = createRequestJobPosition.getPlaceNumber();

            if (!Strings.isEmpty(name) && salaryMin > 0.0 && salaryMax > 0.0 && salaryMin < salaryMax && idArea > 0 && !Strings.isEmpty(description)) {

                int auxVacant = 0;

                if (career == null && newCareer == null) {
                    throw new ExpectationFailedException("the careers lists are empty");
                }

                if (hardSkill == null && newHardSkill == null) {
                    throw new ExpectationFailedException("the skills lists are empty");
                }

                if (processs == null) {
                    throw new ExpectationFailedException("the process list are empty");
                }

                if (recruiter != null && placesNumber > 0) {
                    auxVacant = 1;
                } else {
                    throw new ExpectationFailedException("Vacant data is incorrect");
                }

                if (jobPositionRepository.findAtributes(name, nitCompany, idArea) == null) {

                    /*JOB-POSITION*/
                    
                    JobPosition newJobPosition = new JobPosition();
                    newJobPosition.setName(name.trim());
                    newJobPosition.setSalaryMin(salaryMin);
                    newJobPosition.setSalaryMax(salaryMax);
                    newJobPosition.setIdArea(idArea);
                    newJobPosition.setDescription(description.trim());
                    newJobPosition.setNit(nitCompany.trim());
                    int idJob = jobPositionRepository.save(newJobPosition).getId();

                    /*CAREERS*/
                    
                    if (career != null) {
                        career.stream().map((career1) -> {
                            CareerJob careerJob = new CareerJob();
                            careerJob.setId(String.valueOf(idJob), String.valueOf(career1.getId()));
                            careerJob.setIdjob(idJob);
                            careerJob.setIdCareer(career1.getId());
                            return careerJob;
                        }).forEachOrdered((careerJob) -> {
                            jobCareerRepository.save(careerJob);
                        });

                    }

                    if (newCareer != null) {
                        newCareer.stream().map((newCareer1) -> {
                            Career newC = new Career();
                            newC.setName(newCareer1.getName());
                            return newC;
                        }).map((newC) -> {
                            careerRepository.save(newC);
                            return newC;
                        }).map((newC) -> careerRepository.findByName(newC.getName()).get().getId()).map((idC) -> {
                            CareerJob careerJob = new CareerJob();
                            careerJob.setId(String.valueOf(idJob), String.valueOf(idC));
                            careerJob.setIdjob(idJob);
                            careerJob.setIdCareer(idC);
                            return careerJob;
                        }).forEachOrdered((careerJob) -> {
                            jobCareerRepository.save(careerJob);
                        });
                    }
                    
                    /*SKILLS*/

                    if (hardSkill != null) {  //ASOCIATE HARDSKILL AND JOBPOSITION
                        hardSkill.stream().map((hardSkill1) -> {
                            SkillJob newSkillJob = new SkillJob();
                            newSkillJob.setId(String.valueOf(idJob), String.valueOf(hardSkill1.getId()));
                            newSkillJob.setIdJob(idJob);
                            newSkillJob.setIdSkill(hardSkill1.getId());
                            return newSkillJob;
                        }).forEachOrdered((newSkillJob) -> {
                            jobSkillRepository.save(newSkillJob);
                        });
                    }

                    if (!newHardSkill.isEmpty()) {

                        hardSkill.stream().map((hardSkill1) -> {
                            Skill newHS = new Skill();
                            newHS.setName(hardSkill1.getName());
                            return newHS;
                        }).map((newHS) -> {
                            newHS.setType("Hard");
                            return newHS;
                        }).map((newHS) -> {
                            skillRepository.save(newHS);
                            return newHS;
                        }).map((newHS) -> skillRepository.findByName(newHS.getName()).get().getId()).map((idSkill) -> {
                            SkillJob newSkillJob = new SkillJob();
                            newSkillJob.setId(String.valueOf(idJob), String.valueOf(idSkill));
                            newSkillJob.setIdJob(idJob);
                            newSkillJob.setIdSkill(idSkill);
                            return newSkillJob;
                        }).forEachOrdered((newSkillJob) -> {
                            jobSkillRepository.save(newSkillJob);
                        });

                    }
                    
                    /*PROCESS*/

                    processs.stream().map((proces) -> {
                        Processs newProcess = new Processs();
                        newProcess.setName(proces.getName());
                        return newProcess;
                    }).map((newProcess) -> {
                        newProcess.setId_job_position(idJob);
                        return newProcess;
                    }).forEachOrdered((newProcess) -> {
                        processRepository.save(newProcess);
                    });

                    /*VACANT*/
                    
                    if (auxVacant == 1) {
                        recruiter.forEach((recruiter1) -> {

                            Date date = new Date(System.currentTimeMillis());
                            Vacant newVacant = new Vacant();

                            newVacant.setPlacesNumber(placesNumber);
                            newVacant.setStartDate(date);
                            newVacant.setNitJobPosition(idJob);
                            int idVacant = vacantRepository.save(newVacant).getId();

                            RecruiterVacant rv = new RecruiterVacant();
                            rv.setIdPerson(recruiter1.getId());
                            rv.setIdVacant(idVacant);
                            recruiterVacantRepository.save(rv);
                        });
                    }

                } else {
                    throw new ExpectationFailedException("JobPosition already exist");
                }

            } else {
                throw new ExpectationFailedException("JobPosition data is incorrect");
            }

        } else {
            throw new ExpectationFailedException("the company doesn't exist");
        }
    }
}
