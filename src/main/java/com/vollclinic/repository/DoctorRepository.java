package com.vollclinic.repository;

import com.vollclinic.dto.DataDoctorList;
import com.vollclinic.enums.Specialty;
import com.vollclinic.models.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByIsActiveTrue(Pageable pagination);
    @Query("FROM Doctor d WHERE " +
            "(d.name LIKE CONCAT('%', :name, '%') " +
            "OR d.email LIKE CONCAT('%', :email, '%') " +
            "OR d.dni LIKE CONCAT('%', :dni, '%')" +
            "OR d.specialty LIKE CONCAT('%', :specialty, '%'))" +
            "AND d.isActive=true")
    Page<DataDoctorList> searchByCriteria(@Param("name") String name,
                                          @Param("email") String email,
                                          @Param("dni") String dni,
                                          @Param("specialty") Specialty specialty,
                                          Pageable pagination);

    @Query("FROM Doctor d WHERE " +
            "d.specialty = :specialty " +
            "AND d.id NOT IN( " +
                "SELECT a.doctor.id FROM Appointment a " +
                "WHERE " +
                "a.date = :date" +
                ") " +
            "AND d.isActive=true " +
            "ORDER BY rand() " +
            "LIMIT 1")
    Doctor selectDoctorWithSpecialtyOnDate(@Param("specialty") Specialty specialty, @Param("date") LocalDateTime date);

    @Query("""
            SELECT d.isActive
            FROM Doctor d
            WHERE d.id=:idDoctor
            """)
    Boolean findActiveById(@Param("idDoctor") Long idDoctor);
}
