package com.medilabo.patientms.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.medilabo.patientms.model.Patient;

/**
 * Patient repository interface of Patient microservice (Médilabo)
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	
	
	public List<Patient> findByLastName(String lastName);

	public List<Patient> findByFirstName(String firstName);

	@Query("SELECT p FROM Patient p WHERE p.firstName LIKE %:keyword% OR p.lastName LIKE %:keyword%")
	public List<Patient> findByKeywordContaining(@Param("keyword") String keyword);

}
