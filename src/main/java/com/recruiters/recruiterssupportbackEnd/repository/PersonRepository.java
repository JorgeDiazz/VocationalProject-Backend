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
    
    /**
     * Consulta para obtener postulantes que no tenga un reclutador asignado en
     * postulant_rv siendo su status = 1
     * @param idRv
     * @return  list person
     */
    @Query(nativeQuery = true,value="SELECT person.* FROM person INNER JOIN postulant_rv ON person.id=postulant_rv.id_postulant WHERE postulant_rv.id_rv=?1 AND postulant_rv.state=1")
    List<Person> findPostulantsRv(int idRv);
    
}
