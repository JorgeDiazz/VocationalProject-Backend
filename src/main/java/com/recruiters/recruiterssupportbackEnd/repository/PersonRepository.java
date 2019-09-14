package com.recruiters.recruiterssupportbackEnd.repository;

import com.recruiters.recruiterssupportbackEnd.model.entities.Person;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PersonRepository extends JpaRepository<Person, String> {

    @Query("from Person where email = ?1")
    Optional<Person> findByEmail(String email);
    
    @Query("from Person where nit_company = ?1")
    List<Person> findByNit(String nit);
    
}
