package com.medilabo.patientms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.medilabo.patientms.model.Patient;

public interface PatientRepository  extends JpaRepository<Patient, Integer> {
	
	public List <Patient> findByLastname(String lastname);
	public List <Patient> findByFirstname(String firstname);
	

	
	
}
