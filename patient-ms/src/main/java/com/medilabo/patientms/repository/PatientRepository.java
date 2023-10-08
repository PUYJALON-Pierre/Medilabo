package com.medilabo.patientms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.patientms.model.Patient;

/**
 * Patient repository interface of Patient microservice (Médilabo)
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	public List<Patient> findByLastName(String lastName);

	public List<Patient> findByFirstName(String firstName);

}
