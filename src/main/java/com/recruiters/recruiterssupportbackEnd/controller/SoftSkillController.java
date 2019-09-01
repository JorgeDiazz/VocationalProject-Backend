package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import com.recruiters.recruiterssupportbackEnd.repository.SoftSkillRepository;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/softskills")
public class SoftSkillController {

    private final SoftSkillRepository softSkillRepository;

    @Autowired
    public SoftSkillController(SoftSkillRepository softSkillRepository) {
        this.softSkillRepository = softSkillRepository;
    }

    @GetMapping("/")
    public List<SoftSkill> getAllSoftSkills() {
        return softSkillRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<SoftSkill> createSoftSkill(@Valid @RequestBody SoftSkill softSkill) {
        if (SoftSkill.isCorrectForCreate(softSkill)) {
            softSkillRepository.save(softSkill);
            return HttpResponseEntity.getOKStatus(softSkill);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }

}
