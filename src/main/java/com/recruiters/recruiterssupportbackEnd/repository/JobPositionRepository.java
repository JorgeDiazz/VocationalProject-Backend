package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.JobPosition;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface JobPositionRepository extends JpaRepository<JobPosition, Integer> {

    @Query("from JobPosition where nit_company = ?1")
    public List<JobPosition> findByNit(String nit);

}
