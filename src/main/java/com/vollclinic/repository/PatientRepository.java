package com.vollclinic.repository;

import com.vollclinic.dto.DataPatientList;
import com.vollclinic.models.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Page<Patient> findByIsActiveTrue(Pageable pagination);
    @Query("FROM Patient p WHERE " +
            "(p.name LIKE CONCAT('%', :name, '%') " +
            "OR p.email LIKE CONCAT('%', :email, '%') " +
            "OR p.dni LIKE CONCAT('%', :dni, '%'))" +
            "AND p.isActive=true")
    Page<DataPatientList> searchByCriteria(@Param("name") String name,
                                           @Param("email") String email,
                                           @Param("dni") String dni,
                                           Pageable pagination);
    @Query("""
            SELECT p.isActive
            FROM Patient p
            WHERE p.id=:idPatient
            """)
    Boolean findActiveById(@Param("idPatient") Long idPatient);
}
