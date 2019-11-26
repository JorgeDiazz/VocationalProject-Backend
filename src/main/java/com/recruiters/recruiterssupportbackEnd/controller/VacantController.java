package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ConflictException;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ExpectationFailedException;
import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.CreateVacantRequest;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.VacantByCareer;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerJobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import com.recruiters.recruiterssupportbackEnd.model.entities.RecruiterVacant;
import com.recruiters.recruiterssupportbackEnd.model.entities.Vacant;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.VacantForPostulantWithoutRelation;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.VacantPending;
import com.recruiters.recruiterssupportbackEnd.repository.CareerJobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.JobPositionRepository;
import com.recruiters.recruiterssupportbackEnd.repository.RecruiterVacantRepository;
import com.recruiters.recruiterssupportbackEnd.repository.VacantRepository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.recruiters.recruiterssupportbackEnd.ResponseEntitiesRepository.VacantPendingRepository;
import com.recruiters.recruiterssupportbackEnd.controller.exceptions.ServerException;
import com.recruiters.recruiterssupportbackEnd.controller.request_entities.SelectPostulantsObject;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ApplyVacant;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseVacantApplied;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.GetPostulantsWithoutRecruiter;
import com.recruiters.recruiterssupportbackEnd.model.entities.Career;
import com.recruiters.recruiterssupportbackEnd.model.entities.CareerP;
import com.recruiters.recruiterssupportbackEnd.model.entities.Postulant;
import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
import com.recruiters.recruiterssupportbackEnd.repository.CareerPersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.CareerRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PersonRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRepository;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRvRepository;
import com.recruiters.recruiterssupportbackEnd.repository.SkillRepository;
import java.util.Optional;
import org.springframework.web.bind.annotation.PutMapping;

/**
 *
 * @author JorgeDíaz
 */
@RestController
@RequestMapping("/vacant")
public class VacantController {

    private final VacantRepository vacantRepository;
    private final RecruiterVacantRepository recruiterVacantRepository;
    private final CareerJobPositionRepository careerJobPositionRepository;
    private final JobPositionRepository jobPositionRepository;
    private final VacantPendingRepository vacantPendingRepository;
    private final PostulantRvRepository postulantRvRepository;
    private final SkillRepository skillRepository;
    private final PostulantRepository postulantRepository;
    private final PersonRepository personRepository;
    private final CareerPersonRepository careerPersonRepository;
    private final CareerRepository careerRepository;

    @Autowired
    public VacantController(SkillRepository skillRepository, VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository, CareerJobPositionRepository careerJobPositionRepository, JobPositionRepository jobPositionRepository, VacantPendingRepository vacantPendingRepository, PostulantRvRepository postulantRvRepository, PostulantRepository postulantRepository, PersonRepository personRepository, CareerPersonRepository careerPersonRepository, CareerRepository careerRepository) {
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
        this.careerJobPositionRepository = careerJobPositionRepository;
        this.jobPositionRepository = jobPositionRepository;
        this.vacantPendingRepository = vacantPendingRepository;
        this.postulantRvRepository = postulantRvRepository;
        this.skillRepository = skillRepository;
        this.postulantRepository = postulantRepository;
        this.personRepository = personRepository;
        this.careerPersonRepository = careerPersonRepository;
        this.careerRepository = careerRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Vacant>> getAllVacantByJobPosition(@PathVariable int id) {
        return HttpResponseEntity.getOKStatus(vacantRepository.findByJobPositionId(id));
    }

    @GetMapping("/forPostulant/")
    public ResponseEntity<List<VacantByCareer>> getAllVacantsCareers(@RequestParam List<String> careers) {

        List<Integer> listCareers = careers.stream().map(Integer::valueOf).collect(Collectors.toList());
        List<VacantByCareer> listVacants = new ArrayList<>();

        for (int i = 0; i < listCareers.size(); i++) {

            List<VacantByCareer> vacantsByCareers = vacantPendingRepository.findAllVacantsByCareer(listCareers.get(i));

            if (!vacantsByCareers.isEmpty()) {

                for (int j = 0; j < vacantsByCareers.size(); j++) {
                    listVacants.add(vacantsByCareers.get(j));
                }
            }
        }

        return HttpResponseEntity.getOKStatus(listVacants);
    }

    /**
     * Obtiene las vacantes pendientes para el reclutador, Aquellas que no
     * tienen asignada ninguna habilidad blanda.
     *
     * @param id
     * @return
     */
    @GetMapping("/pending/{id}")
    public ResponseEntity<List<VacantPending>> getAllVacantPending(@PathVariable String id) throws ServerException {

        List<RecruiterVacant> opt = recruiterVacantRepository.findByRecruiter(id);
        List<VacantPending> listVacantPending = new ArrayList<>();
        if (!opt.isEmpty()) {
            for (int i = 0; i < opt.size(); i++) {
                int idVacant = opt.get(i).getIdVacant();

                Vacant vacant = vacantRepository.findById(idVacant).get();
                int idJob = vacant.getNitJobPosition();

                JobPosition jobPosition = jobPositionRepository.findById(idJob).get();
                String nameJobPosition = jobPosition.getName();
                if (skillRepository.CountSoftJobPosition(jobPosition.getId()) > 0) {
                    continue;
                }

                try {
                    VacantPending vacantPending = new VacantPending();
                    vacantPending.setId(idVacant);
                    vacantPending.setIdJobPosition(jobPosition.getId()); // Jhoan 29/10/2019
                    vacantPending.setJobPositionName(nameJobPosition);
                    vacantPending.setRecruitersNumber(recruiterVacantRepository.numRecuitersVacant(idVacant));
                    vacantPending.setStartDate(vacant.getStartDate());
                    vacantPending.setPlacesNumber(vacant.getPlacesNumber());
                    listVacantPending.add(vacantPending);
                } catch (Exception e) {
                    throw new ServerException();
                }

            }
            return HttpResponseEntity.getOKStatus(listVacantPending);

        } else {
            return HttpResponseEntity.getOKStatus(listVacantPending);
        }
    }

    /**
     * Obtiene las vacantes en proceso para el reclutador, Aquellas que tienen
     * asignadas habilidades blandas.
     *
     * @param id
     * @return
     */
    @GetMapping("/process/{idRecruiter}")
    public ResponseEntity<List<VacantPending>> getAllVacantInProcess(@PathVariable String idRecruiter) throws ServerException {

        List<RecruiterVacant> opt = recruiterVacantRepository.findByRecruiter(idRecruiter);
        List<VacantPending> listVacantPending = new ArrayList<>();
        if (!opt.isEmpty()) {

            for (int i = 0; i < opt.size(); i++) {

                int idVacant = opt.get(i).getIdVacant();

                Vacant vacant = vacantRepository.findById(idVacant).get();
                int idJob = vacant.getNitJobPosition();

                JobPosition jobPosition = jobPositionRepository.findById(idJob).get();
                String nameJobPosition = jobPosition.getName();
                if (skillRepository.CountSoftJobPosition(jobPosition.getId()) == 0) {
                    continue;
                }

                try {
                    VacantPending vacantPending = new VacantPending();
                    vacantPending.setId(idVacant);
                    vacantPending.setIdJobPosition(jobPosition.getId()); // Jhoan 29/10/2019
                    vacantPending.setJobPositionName(nameJobPosition);
                    vacantPending.setRecruitersNumber(recruiterVacantRepository.numRecuitersVacant(idVacant));
                    vacantPending.setStartDate(vacant.getStartDate());
                    vacantPending.setPlacesNumber(vacant.getPlacesNumber());
                    listVacantPending.add(vacantPending);
                } catch (Exception e) {
                    throw new ServerException();
                }

            }

            return HttpResponseEntity.getOKStatus(listVacantPending);

        } else {
            return HttpResponseEntity.getOKStatus(listVacantPending);
        }
    }

    @GetMapping("/withoutRelation/{id_career}")
    public ResponseEntity<List<VacantForPostulantWithoutRelation>> getVacantsByCareer(@PathVariable int id_career) throws Throwable {

        List<CareerJobPosition> careerJobPositionList = careerJobPositionRepository.findByCareerId(id_career);

        if (!careerJobPositionList.isEmpty()) {
            List<VacantForPostulantWithoutRelation> vacantForPostulantWithoutRelationList = new ArrayList<>();

            for (CareerJobPosition careerJobPosition : careerJobPositionList) {

                int jobPositionId = careerJobPosition.getJobPositionId();
                JobPosition jobPosition = jobPositionRepository.findById(jobPositionId).get();

                List<Vacant> vacants = vacantRepository.findByJobPositionId(jobPositionId);

                VacantForPostulantWithoutRelation newVacant;
                for (Vacant vacant : vacants) {
                    newVacant = new VacantForPostulantWithoutRelation();
                    newVacant.setId(vacant.getId());
                    newVacant.setStartDate(vacant.getStartDate());
                    newVacant.setEndDate(vacant.getEndDate());
                    newVacant.setPlacesNumber(vacant.getPlacesNumber());
                    newVacant.setJobPositionName(jobPosition.getName());
                    newVacant.setMaxSalary(jobPosition.getSalaryMax());
                    newVacant.setMinSalary(jobPosition.getSalaryMin());
                    vacantForPostulantWithoutRelationList.add(newVacant);
                }
            }
            return HttpResponseEntity.getOKStatus(vacantForPostulantWithoutRelationList);
        } else {
            throw new ConflictException("Career doesn't exist on career_job_position data");
        }
    }

    @PostMapping("/")
    public ResponseEntity<Vacant> createVacant(@RequestBody CreateVacantRequest newVacant) throws Throwable {

        Vacant vacant = new Vacant();
        vacant.setPlacesNumber(newVacant.getPlacesNumber());
        vacant.setIdJobPosition(newVacant.getIdJobPosition());

        Date date = new Date(System.currentTimeMillis());
        vacant.setStartDate(date);

        try {
            vacantRepository.save(vacant);
        } catch (Exception e) {
            throw new ExpectationFailedException("Vacant data is incorrect");
        }
        try {
            RecruiterVacant recruiterVacant;
            for (String idRecruiter : newVacant.getRecruitersId()) {
                recruiterVacant = new RecruiterVacant();
                recruiterVacant.setIdPerson(idRecruiter);
                recruiterVacant.setIdVacant(vacant.getId());
                recruiterVacantRepository.save(recruiterVacant);
            }
        } catch (Exception e) {
            throw new ConflictException("Any recruiter doesn't exist");
        }

        return HttpResponseEntity.getOKStatus(vacant);

    }

    @PostMapping("/Apply")
    public ResponseEntity ApplyVacant(@RequestBody ApplyVacant applyVacant) throws ExpectationFailedException, ConflictException {
        int idVacant = applyVacant.getIdVacant();
        String idPostulant = applyVacant.getIdPostulant();

        try {
            postulantRvRepository.insert(0, idPostulant, idVacant);
        } catch (Exception e) {
            throw new ExpectationFailedException("PostulantRv data is incorrect");
        }
        System.out.println("Correcto");
        return HttpResponseEntity.getOKStatus(applyVacant);
    }

    @GetMapping("/applied/{idPostulant}")
    public ResponseEntity<List<CreateResponseVacantApplied>> getApplyVacants(@PathVariable String idPostulant) throws ExpectationFailedException, ConflictException {

        List<PostulantRv> Listpostulant = postulantRvRepository.findByPostulant(idPostulant);
        if (!Listpostulant.isEmpty()) {

            List<CreateResponseVacantApplied> applyvacants = new ArrayList<>();
            for (PostulantRv postulantrv : Listpostulant) {

                int idVacant = postulantrv.getIdVacant();

                Vacant vacant = vacantRepository.findById(idVacant).get();
                int idJob = vacant.getNitJobPosition();

                JobPosition jobPosition = jobPositionRepository.findById(idJob).get();
                String nameJobPosition = jobPosition.getName();

                try {
                    CreateResponseVacantApplied vacantApply = new CreateResponseVacantApplied();
                    vacantApply.setId(idVacant);
                    vacantApply.setNameJob(nameJobPosition);
                    vacantApply.setSalaryMax(jobPosition.getSalaryMax());
                    vacantApply.setSalaryMin(jobPosition.getSalaryMin());
                    vacantApply.setStartDate(vacant.getStartDate());
                    vacantApply.setPlacesNumber(vacant.getPlacesNumber());
                    vacantApply.setState(postulantrv.getState());
                    /*switch (postulantrv.getState()) {
                        case 0: //revisar que en la db id_rv pueda ser null
                            vacantApply.setState(postulantrv.getState());
                            break;
                        case 1:
                            vacantApply.setState(postulantrv.getState());
                            break;
                        case 2:
                            vacantApply.setState(postulantrv.getState());
                            break;
                        default: vacantApply.setState(postulantrv.getState());
                    }*/
                    applyvacants.add(vacantApply);
                } catch (Exception e) {
                    throw new ExpectationFailedException("Vacant Apply data is incorrect");
                }
            }
            return HttpResponseEntity.getOKStatus(applyvacants);
        } else {
            throw new ConflictException("postulant dosen't have any vacanats");
        }
    }

    @GetMapping("/getPostulants/{idVacant}")
    public ResponseEntity<List<GetPostulantsWithoutRecruiter>> getPostulantsWithoutRecruiters(@PathVariable int idVacant) throws Throwable {

        List<PostulantRv> postulantsRv = postulantRvRepository.findByVacant(idVacant);
        List<GetPostulantsWithoutRecruiter> postulantsWithoutRecruiterList = new ArrayList();

        for (PostulantRv postulantRv : postulantsRv) {
            if (postulantRv.getState() == 0) {
                String idPostulant = postulantRv.getIdPostulant();
                Optional<Postulant> optPostulant = postulantRepository.findById(idPostulant);
                if (optPostulant.isPresent()) {
                    Postulant currentPostulant = optPostulant.get();

                    GetPostulantsWithoutRecruiter postulantWithoutRecruiter = new GetPostulantsWithoutRecruiter();

                    String idPerson = currentPostulant.getIdPerson();
                    postulantWithoutRecruiter.setId(idPerson);
                    postulantWithoutRecruiter.setName(personRepository.findById(idPerson).get().getName());

                    List<CareerP> careerPList = careerPersonRepository.findByIdPersonforlist(idPerson);
                    for (CareerP careerP : careerPList) {
                        int idCareer = careerP.getIdCareer();
                        Optional<Career> career = careerRepository.findById(idCareer);
                        if (career.isPresent()) {
                            postulantWithoutRecruiter.getCareers().add(career.get().getName());
                        }
                    }

                    postulantsWithoutRecruiterList.add(postulantWithoutRecruiter);
                }
            }

        }

        return HttpResponseEntity.getOKStatus(postulantsWithoutRecruiterList);
    }

    @PutMapping("/selectPostulants")
    public ResponseEntity selectPostulants(@RequestBody SelectPostulantsObject selectPostulantsObject) {

        for (String postulantId : selectPostulantsObject.getPostulants()) {
            List<PostulantRv> postulantRvList = postulantRvRepository.findByPostulant(postulantId);
            for (PostulantRv postulantRv : postulantRvList) {
                if (selectPostulantsObject.getIdVacant() == postulantRv.getIdVacant() && selectPostulantsObject.getIdRv() == postulantRv.getIdRv()) {
                    postulantRv.setState(1);
                    postulantRvRepository.save(postulantRv);
                }
            }

        }
        return HttpResponseEntity.getOKStatus();
    }

}
