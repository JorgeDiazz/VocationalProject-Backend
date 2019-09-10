
package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.skills.Skill;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author jhanu
 */
public interface SkillRepository extends JpaRepository<Skill, Integer>{
    
}
