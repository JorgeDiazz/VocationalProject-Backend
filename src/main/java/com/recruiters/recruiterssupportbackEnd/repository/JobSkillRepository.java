
package com.recruiters.recruiterssupportbackEnd.repository;
import com.recruiters.recruiterssupportbackEnd.model.entities.Skill;
import com.recruiters.recruiterssupportbackEnd.model.entities.SkillJob;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
/**
 *
 * @author katemorales
 */
public interface JobSkillRepository extends JpaRepository<SkillJob, String>{
    
    @Query("from Skill where id = ?1")
    List<Skill> findLocalJob(int id);
}
