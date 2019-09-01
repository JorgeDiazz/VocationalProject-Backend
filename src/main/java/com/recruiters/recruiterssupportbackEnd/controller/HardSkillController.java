package com.recruiters.recruiterssupportbackEnd.controller;

import com.recruiters.recruiterssupportbackEnd.controller.http.HttpResponseEntity;
import com.recruiters.recruiterssupportbackEnd.model.entities.skills.HardSkill;
import com.recruiters.recruiterssupportbackEnd.repository.HardSkillRepository;
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
@RequestMapping("/hardskills")
public class HardSkillController {

    private final HardSkillRepository hardSkillRepository;

    @Autowired
    public HardSkillController(HardSkillRepository hardSkillRepository) {
        this.hardSkillRepository = hardSkillRepository;
    }

    @GetMapping("/")
    public List<HardSkill> getAllHardSkills() {
        return hardSkillRepository.findAll();
    }

    @PostMapping("/")
    public ResponseEntity<HardSkill> createHardSkill(@Valid @RequestBody HardSkill hardSkill) {
        if (HardSkill.isCorrectForCreate(hardSkill)) {
            hardSkillRepository.save(hardSkill);
            return HttpResponseEntity.getOKStatus(hardSkill);
        }

        return HttpResponseEntity.getMissingFieldsStatus();
    }

}
