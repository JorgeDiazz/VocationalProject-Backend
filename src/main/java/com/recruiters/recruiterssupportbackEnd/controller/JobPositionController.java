package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ConflictException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateJobPositionRequest;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateRequestJobRec;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseInProcess;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ResponseGetJobPositionByNit;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ResponseGetSpecificJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Area;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobSkill;
import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
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
import com.recruiters.recruiterssupportbackEnd.repository.AreaRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRvRepository;
import com.recruiters.recruiterssupportbackEnd.repository.ProcessRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
    private final PersonRepository personRepository;
    private final PostulantRvRepository postulantRvRepository;
    private final AreaRepository areaRepository;

    @Autowired
    public JobPositionController(JobPositionRepository jobPositionRepository, CareerJobPositionRepository careerJobPositionRepository, CareerRepository careerRepository, SkillRepository skillRepository, JobSkillRepository jobSkillRepository, VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository, ProcessRepository processRepository, PersonRepository personRepository, PostulantRvRepository postulantRvRepository, AreaRepository areaRepository) {
        this.jobPositionRepository = jobPositionRepository;
        this.careerJobPositionRepository = careerJobPositionRepository;
        this.careerRepository = careerRepository;
        this.skillRepository = skillRepository;
        this.jobSkillRepository = jobSkillRepository;
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
        this.processRepository = processRepository;
        this.personRepository = personRepository;
        this.postulantRvRepository = postulantRvRepository;
        this.areaRepository = areaRepository;
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
                jobSkill.setId(jobPosition.getId(), newHardSkillId);
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
            throw new ExpectationFailedException(e.getMessage());
        }

        return HttpResponseEntity.getOKStatus();
    }

    @GetMapping("/{nit}")
    public ResponseEntity<List<ResponseGetJobPositionByNit>> getJobPositionByNit(@PathVariable String nit) throws Throwable {

        List<ResponseGetJobPositionByNit> response = new ArrayList<>();

        List<JobPosition> jobPositionList = jobPositionRepository.findByNit(nit);

        if (jobPositionList != null) {
            for (JobPosition jobPosition : jobPositionList) {

                ResponseGetJobPositionByNit respJobPosition = new ResponseGetJobPositionByNit();
                respJobPosition.setIdJobPosition(jobPosition.getId());
                respJobPosition.setName(jobPosition.getName());
                respJobPosition.setSalaryMax(jobPosition.getSalaryMax());
                respJobPosition.setSalaryMin(jobPosition.getSalaryMin());

                List<Vacant> vacants = vacantRepository.findByJobPositionId(jobPosition.getId());
                for (Vacant vacant : vacants) {
                    if (vacant.getPlacesNumber() > 0) {
                        ResponseGetJobPositionByNit.Vacant newVacant = new ResponseGetJobPositionByNit.Vacant();
                        newVacant.setId(vacant.getId());
                        newVacant.setPlacesNumber(vacant.getPlacesNumber());
                        respJobPosition.getVacants().add(newVacant);
                    }
                }

                response.add(respJobPosition);
            }
        } else {
            throw new ConflictException("Job position isn't present");
        }

        return HttpResponseEntity.getOKStatus(response);
    }

    @GetMapping("job/{id}")
    public ResponseEntity<ResponseGetSpecificJobPosition> getJobPositionById(@PathVariable int id) throws Throwable {

        Optional<JobPosition> optJobPosition = jobPositionRepository.findById(id);

        if (optJobPosition.isPresent()) {

            ResponseGetSpecificJobPosition responseGetSpecificJobPosition = new ResponseGetSpecificJobPosition();

            JobPosition jobPosition = optJobPosition.get();
            responseGetSpecificJobPosition.setId(id);
            responseGetSpecificJobPosition.setName(jobPosition.getName());
            responseGetSpecificJobPosition.setSalaryMin(jobPosition.getSalaryMin());
            responseGetSpecificJobPosition.setSalaryMax(jobPosition.getSalaryMax());
            responseGetSpecificJobPosition.setDescription(jobPosition.getDescription());

            // set area
            Optional<Area> optArea = areaRepository.findById(jobPosition.getIdArea());
            Area area = optArea.get();

            responseGetSpecificJobPosition.setIdArea(area.getId());
            responseGetSpecificJobPosition.setNameArea(area.getName());

            // set careers
            List<ResponseGetSpecificJobPosition.Career> careerList = new ArrayList<>();
            List<CareerJobPosition> careerJobPositionList = careerJobPositionRepository.findByJobPositionId(id);

            if (careerJobPositionList != null) {
                for (CareerJobPosition careerJobPosition : careerJobPositionList) {
                    Optional<Career> optCareer = careerRepository.findById(careerJobPosition.getCareerId());
                    Career career = optCareer.get();

                    ResponseGetSpecificJobPosition.Career respCareer = new ResponseGetSpecificJobPosition.Career();
                    respCareer.setId(career.getId());
                    respCareer.setName(career.getName());

                    careerList.add(respCareer);
                }
            }

            responseGetSpecificJobPosition.setCareers(careerList);

            // set skills
            List<ResponseGetSpecificJobPosition.Skill> hardSkillList = new ArrayList<>();
            List<ResponseGetSpecificJobPosition.Skill> softSkillList = new ArrayList<>();

            List<JobSkill> jobSkillList = jobSkillRepository.findbyJobPositionId(id);
            if (jobSkillList != null) {
                for (JobSkill jobSkill : jobSkillList) {
                    Optional<Skill> optSkill = skillRepository.findById(jobSkill.getIdSkill());
                    Skill skill = optSkill.get();

                    ResponseGetSpecificJobPosition.Skill respSkill = new ResponseGetSpecificJobPosition.Skill();
                    respSkill.setId(skill.getId());
                    respSkill.setName(skill.getName());

                    if (skill.getType().equalsIgnoreCase(Skill.TYPE.HARD.name())) {
                        hardSkillList.add(respSkill);
                    } else {
                        softSkillList.add(respSkill);
                    }
                }
            }

            responseGetSpecificJobPosition.setHardSkills(hardSkillList);
            responseGetSpecificJobPosition.setSoftSkills(softSkillList);

            // set processes
            List<ResponseGetSpecificJobPosition.Process> processes = new ArrayList<>();

            List<Process> processList = processRepository.findByJobPositionId(id);
            if (processList != null) {
                for (Process process : processList) {
                    ResponseGetSpecificJobPosition.Process mProcess = new ResponseGetSpecificJobPosition.Process();
                    mProcess.setId(process.getId());
                    mProcess.setName(process.getName());

                    processes.add(mProcess);
                }
            }

            responseGetSpecificJobPosition.setProcesses(processes);

            // set vacants
            List<ResponseGetSpecificJobPosition.Vacant> vacants = new ArrayList<>();

            List<Vacant> vacantList = vacantRepository.findByJobPositionId(id);
            if (vacantList != null) {
                for (Vacant vacant : vacantList) {
                    ResponseGetSpecificJobPosition.Vacant mVacant = new ResponseGetSpecificJobPosition.Vacant();
                    mVacant.setPlacesNumber(vacant.getPlacesNumber());
                    mVacant.setStartDate(vacant.getStartDate());

                    // get recruiters
                    List<ResponseGetSpecificJobPosition.Vacant.Recruiter> recruiters = new ArrayList<>();
                    List<RecruiterVacant> recruiterVacantList = recruiterVacantRepository.findByVacantId(vacant.getId());
                    if (recruiterVacantList != null) {
                        for (RecruiterVacant recruiterVacant : recruiterVacantList) {
                            Optional<Person> optPerson = personRepository.findById(recruiterVacant.getIdPerson());
                            Person person = optPerson.get();

                            ResponseGetSpecificJobPosition.Vacant.Recruiter recruiter = new ResponseGetSpecificJobPosition.Vacant.Recruiter();
                            recruiter.setId(person.getId());
                            recruiter.setName(person.getName());

                            // get postulants
                            List<ResponseGetSpecificJobPosition.Vacant.Recruiter.Postulant> postulants = new ArrayList<>();
                            List<PostulantRv> postulantRvList = postulantRvRepository.findByRV(recruiterVacant.getId());
                            if (postulantRvList != null) {
                                for (PostulantRv postulantRv : postulantRvList) {
                                    Optional<Person> optPostulant = personRepository.findById(postulantRv.getIdPostulant());
                                    Person postulant = optPostulant.get();
                                    ResponseGetSpecificJobPosition.Vacant.Recruiter.Postulant mPostulant = new ResponseGetSpecificJobPosition.Vacant.Recruiter.Postulant();
                                    mPostulant.setIdPerson(postulant.getId());
                                    mPostulant.setName(postulant.getName());
                                    postulants.add(mPostulant);
                                }
                            }

                            recruiter.setPostulants(postulants);
                            recruiters.add(recruiter);
                        }
                    }
                    mVacant.setRecruiters(recruiters);

                    vacants.add(mVacant);
                }
            }
            responseGetSpecificJobPosition.setVacants(vacants);

            return HttpResponseEntity.getOKStatus(responseGetSpecificJobPosition);
        } else {
            throw new ConflictException("Job position isn't present");
        }
    }

    @PostMapping("/inprocess/")
    public ResponseEntity<CreateResponseInProcess> getJobPositionInProcess(@RequestBody CreateRequestJobRec jobrec) throws Throwable {

        Optional<JobPosition> optJobPosition = jobPositionRepository.findById(jobrec.getIdJob());

        if (optJobPosition.isPresent()) {
            CreateResponseInProcess myinprocess = new CreateResponseInProcess();
            JobPosition jobPosition = optJobPosition.get();

            // set area
            Optional<Area> optArea = areaRepository.findById(jobPosition.getIdArea());
            Area area = optArea.get();

            myinprocess.setArea(area.getName());

            //set salary
            myinprocess.setSalaryMin(jobPosition.getSalaryMin());
            myinprocess.setSalaryMax(jobPosition.getSalaryMax());

            // set careers
            List<String> careerList = new ArrayList<>();
            List<CareerJobPosition> careerJobPositionList = careerJobPositionRepository.findByJobPositionId(jobrec.getIdJob());

            if (careerJobPositionList != null) {
                for (CareerJobPosition careerJobPosition : careerJobPositionList) {
                    Optional<Career> optCareer = careerRepository.findById(careerJobPosition.getCareerId());
                    Career career = optCareer.get();
                    careerList.add(career.getName());
                }
            }
            myinprocess.setCareers(careerList);

            // set processes
            List<String> processes = new ArrayList<>();

            List<Process> processList = processRepository.findByJobPositionId(jobrec.getIdJob());
            if (processList != null) {
                for (Process process : processList) {
                    processes.add(process.getName());
                }
            }
            myinprocess.setProcesses(processes);

            // set skills
            List<String> hardSkillList = new ArrayList<>();
            List<String> softSkillList = new ArrayList<>();

            List<JobSkill> jobSkillList = jobSkillRepository.findbyJobPositionId(jobrec.getIdJob());
            if (jobSkillList != null) {
                for (JobSkill jobSkill : jobSkillList) {
                    Optional<Skill> optSkill = skillRepository.findById(jobSkill.getIdSkill());
                    Skill skill = optSkill.get();

                    if (skill.getType().equalsIgnoreCase(Skill.TYPE.HARD.name())) {
                        hardSkillList.add(skill.getName());
                    } else {
                        softSkillList.add(skill.getName());
                    }
                }
            }

            myinprocess.setHardSkills(hardSkillList);
            myinprocess.setSoftSkills(softSkillList);

            //set postulants and placenumbers by vacant
            List<String> postulants = new ArrayList<>();

            List<Vacant> vacantList = vacantRepository.findByJobPositionId(jobrec.getIdJob());
            if (vacantList != null) {
                for (Vacant vacant : vacantList) {

                    // find recruiter vacants
                    List<RecruiterVacant> recruitervac = recruiterVacantRepository.findById(vacant.getId());

                    for (RecruiterVacant recvac : recruitervac) {

                        if (recvac.getIdPerson().equals(jobrec.getIdRecruiter())) {

                            myinprocess.setPlacesNumber(myinprocess.getPlacesNumber() + vacant.getPlacesNumber()); 
                            
                            List<PostulantRv> myprv = postulantRvRepository.findByRV(recvac.getId());

                            for (PostulantRv prv : myprv) {
                                Optional<Person> postulant =personRepository.findById(prv.getIdPostulant());
                                Person personita=postulant.get();
                                postulants.add(personita.getName());

                            }
                        }
                    }
                }
            }

            myinprocess.setPostulants(postulants);

            return HttpResponseEntity.getOKStatus(myinprocess);
        } else {
            throw new ConflictException("Job position isn't present");
        }
    }
}
