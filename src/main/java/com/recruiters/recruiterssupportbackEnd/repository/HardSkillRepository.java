package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.HardSkill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HardSkillRepository extends MongoRepository<HardSkill, String> {

}
