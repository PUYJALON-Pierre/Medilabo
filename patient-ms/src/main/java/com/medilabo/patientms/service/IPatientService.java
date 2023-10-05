package com.medilabo.patientms.service;

import java.util.List;

import com.medilabo.patientms.model.Patient;

public interface IPatientService {

	
	List<Patient> getAllPatients();
	
	List<Patient> getPatientByLastName(String lastName);
	
	List<Patient> getPatientByFirstName(String firstName);
	
    Patient getPatientById(int id) throws Exception;
	
	Patient savePatient(Patient patient);
	
    Patient updatePatient(Patient patient) throws Exception;
	
    Patient deletePatientById(int id) throws Exception;
}
