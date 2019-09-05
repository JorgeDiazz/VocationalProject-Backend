package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SoftSkillRepository extends JpaRepository<SoftSkill, String> {

}
