package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateJobPositionRequest;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.repository.CareerJobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.CareerRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobSkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.RecruiterVacantRepository;
import com.recruiters.recruiterssupportbackEnd.repository.SkillRepository;
import com.recruiters.recruiterssupportbackEnd.repository.VacantRepository;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.recruiters.recruiterssupportbackEnd.model.entities.Process;
import com.recruiters.recruiterssupportbackEnd.repository.ProcessRepository;

/**
 *
 * @author JorgeDíaz
 */
@RestController
@RequestMapping("/jobPosition")
public class JobPositionController {

    private final JobPositionRepository jobPositionRepository;
    private final CareerJobPositionRepository careerJobPositionRepository;
    private final CareerRepository careerRepository;
    private final SkillRepository skillRepository;
    private final JobSkillRepository jobSkillRepository;
    private final VacantRepository vacantRepository;
    private final RecruiterVacantRepository recruiterVacantRepository;
    private final ProcessRepository processRepository;

    @Autowired
    public JobPositionController(JobPositionRepository jobPositionRepository, CareerJobPositionRepository careerJobPositionRepository, CareerRepository careerRepository, SkillRepository skillRepository, JobSkillRepository jobSkillRepository, VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository, ProcessRepository processRepository) {
        this.jobPositionRepository = jobPositionRepository;
        this.careerJobPositionRepository = careerJobPositionRepository;
        this.careerRepository = careerRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
        this.processRepository = processRepository;
    }

    @PostMapping("/")
    public ResponseEntity<JobPosition> createJobPosition(@RequestBody CreateJobPositionRequest newJobPosition) throws Throwable {

        try {
            JobPosition jobPosition = new JobPosition();
            jobPosition.setName(newJobPosition.getName());
            jobPosition.setSalaryMin(newJobPosition.getSalaryMin());
            jobPosition.setSalaryMax(newJobPosition.getSalaryMax());
            jobPosition.setDescription(newJobPosition.getDescription());
            jobPosition.setNit(newJobPosition.getNitCompany());
            jobPosition.setIdArea(newJobPosition.getIdArea());

            jobPositionRepository.save(jobPosition);

            int i = 0;
            int[] newCareersId1 = new int[newJobPosition.getNewCareersName().length];

            Career career;
            for (String careerName : newJobPosition.getNewCareersName()) {
                career = new Career();
                career.setName(careerName);
                careerRepository.save(career);
                newCareersId1[i++] = career.getId();
            }

            int[] newCareersId2 = newJobPosition.getCareersId();

            int[] careersId = new int[newCareersId2.length + newCareersId1.length];

            for (i = 0; i < newCareersId2.length; i++) {
                careersId[i] = newCareersId2[i];
            }

            int j;
            for (j = 0; j < newCareersId1.length; j++, i++) {
                careersId[i] = newCareersId1[j];
            }

            CareerJobPosition careerJobPosition;
            for (int careerId : careersId) {
                careerJobPosition = new CareerJobPosition();
                careerJobPosition.setCareerId(careerId);
                careerJobPosition.setJobPositionId(jobPosition.getId());
                careerJobPositionRepository.save(careerJobPosition);
            }

            i = 0;
            int[] hardSkillsId1 = new int[newJobPosition.getNewHardSkillsName().length];

            Skill skill;
            for (String hardSkillName : newJobPosition.getNewHardSkillsName()) {
                skill = new Skill();
                skill.setName(hardSkillName);
                skill.setType(Skill.TYPE.HARD.name());
                skillRepository.save(skill);
                hardSkillsId1[i++] = skill.getId();
            }

            int[] hardSkillsId2 = newJobPosition.getHardSkillsId();

            int[] newHardSkills = new int[hardSkillsId1.length + hardSkillsId2.length];

            for (j = 0; j < hardSkillsId1.length; j++) {
                newHardSkills[j] = hardSkillsId1[j];
            }

            for (i = 0; i < hardSkillsId2.length; i++, j++) {
                newHardSkills[j] = hardSkillsId2[i];
            }

            JobSkill jobSkill;
            for (int newHardSkillId : newHardSkills) {
                jobSkill = new JobSkill();
                jobSkill.setIdJob(jobPosition.getId());
                jobSkill.setIdSkill(newHardSkillId);
                jobSkillRepository.save(jobSkill);
            }

            Vacant vacant = new Vacant();
            vacant.setPlacesNumber(newJobPosition.getPlacesNumber());
            vacant.setStartDate(new Date(System.currentTimeMillis()));
            vacant.setIdJobPosition(jobPosition.getId());
            vacantRepository.save(vacant);

            RecruiterVacant recruiterVacant;
            for (String recruiterId : newJobPosition.getRecruitersId()) {
                recruiterVacant = new RecruiterVacant();
                recruiterVacant.setIdPerson(recruiterId);
                recruiterVacant.setIdVacant(vacant.getId());
                recruiterVacantRepository.save(recruiterVacant);
            }

            Process process;
            for (String processName : newJobPosition.getProcessesName()) {
                process = new Process();
                process.setName(processName);
                process.setId_job_position(jobPosition.getId());
                processRepository.save(process);
            }
        } catch (Exception e) {
            throw new ExpectationFailedException("Data is incorrect/repeated by database");
        }

        return HttpResponseEntity.getOKStatus();
    }
}