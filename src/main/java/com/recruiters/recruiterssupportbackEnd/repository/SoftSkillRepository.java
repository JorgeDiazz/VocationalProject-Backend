package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.SoftSkill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface SoftSkillRepository extends MongoRepository<SoftSkill, String> {

}
