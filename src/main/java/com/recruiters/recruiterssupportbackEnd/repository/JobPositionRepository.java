package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author katemorales
 */

public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {

    @Query("from JobPosition where nameJ=?1 and nit_company=?2 and id_area=?3")
    JobPosition findAtributes(String name, String nitCompany, int idArea);

    @Query("from JobPosition where nit_company=?1")
    List<JobPosition> findJobs(String nit);
}
