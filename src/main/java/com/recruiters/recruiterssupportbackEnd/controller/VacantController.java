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
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.ApplyVacant;
import com.recruiters.recruiterssupportbackEnd.controller.response_entities.CreateResponseVacantApplied;
import com.recruiters.recruiterssupportbackEnd.model.entities.PostulantRv;
import com.recruiters.recruiterssupportbackEnd.repository.PostulantRvRepository;
import java.util.Optional;

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

    @Autowired
    public VacantController(VacantRepository vacantRepository, RecruiterVacantRepository recruiterVacantRepository, CareerJobPositionRepository careerJobPositionRepository, JobPositionRepository jobPositionRepository, VacantPendingRepository vacantPendingRepository, PostulantRvRepository postulantRvRepository) {
        this.vacantRepository = vacantRepository;
        this.recruiterVacantRepository = recruiterVacantRepository;
        this.careerJobPositionRepository = careerJobPositionRepository;
        this.jobPositionRepository = jobPositionRepository;
        this.vacantPendingRepository = vacantPendingRepository;
        this.postulantRvRepository = postulantRvRepository;
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

    @GetMapping("/pending/{id}")
    public ResponseEntity<List<VacantPending>> getAllVacantPending(@PathVariable String id) throws ConflictException, ExpectationFailedException {

        List<RecruiterVacant> opt = recruiterVacantRepository.findByRecruiter(id);

        if (!opt.isEmpty()) {

            List<VacantPending> listVacantPending = new ArrayList<>();

            for (int i = 0; i < opt.size(); i++) {

                int idVacant = opt.get(i).getIdVacant();

                Vacant vacant = vacantRepository.findById(idVacant).get();
                int idJob = vacant.getNitJobPosition();

                JobPosition jobPosition = jobPositionRepository.findById(idJob).get();
                String nameJobPosition = jobPosition.getName();

                try {
                    VacantPending vacantPending = new VacantPending();
                    vacantPending.setId(idVacant);
                    vacantPending.setJobPositionName(nameJobPosition);
                    vacantPending.setRecruitersNumber(recruiterVacantRepository.numRecuitersVacant(idVacant));
                    vacantPending.setStartDate(vacant.getStartDate());
                    vacantPending.setPlacesNumber(vacant.getPlacesNumber());
                    listVacantPending.add(vacantPending);
                } catch (Exception e) {
                    throw new ExpectationFailedException("Vacant Pending data is incorrect");
                }

            }

            return HttpResponseEntity.getOKStatus(listVacantPending);

        } else {
            throw new ConflictException("Any Vacant Pending doesn't exist");
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

        List<RecruiterVacant> listRecruiterVacant = recruiterVacantRepository.findByVacantId(idVacant);

        if (!listRecruiterVacant.isEmpty()) {

            for (int i = 0; i < listRecruiterVacant.size(); i++) {

                PostulantRv postulantRv = new PostulantRv();

                try {
                    int idRV = listRecruiterVacant.get(i).getId();
                    postulantRv.setState(0);
                    postulantRv.setIdPostulant(idPostulant);
                    postulantRv.setIdRv(idRV);
                    postulantRv.setIdVacant(idVacant);
                    postulantRvRepository.save(postulantRv);
                } catch (Exception e) {
                    throw new ExpectationFailedException("PostulantRv data is incorrect");
                }
            }

        } else {
            throw new ConflictException("Any recruiterVacant doesn't exist");
        }

        return HttpResponseEntity.getOKStatus();
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
}
