package com.medilabo.patientms.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.medilabo.patientms.exception.PatientNotFoundException;
import com.medilabo.patientms.model.Patient;

/**
 * Interface for services operations about Patient in patient microservice
 * (Médilabo)
 *
 */

public interface IPatientService {

	/**
	 * Retrieve a list of all Patient
	 * 
	 * @return List of Patient
	 */
	List<Patient> getAllPatients();

	/**
	 * Retrieve a Patient by his lastName
	 * 
	 * @param lastName - String
	 * @return Patient
	 */
	List<Patient> getPatientByLastName(String lastName);

	/**
	 * Retrieve a list of Patients by keyword matching lastName or firstName
	 * 
	 * @param keyword - String
	 * @return List of Patient
	 */
	List<Patient> getPatientByLastNameOrFirstName(String keyword);

	/**
	 * Retrieve a Patient by his firstName
	 * 
	 * @param firstName - String
	 * @return List of Patient
	 */
	List<Patient> getPatientByFirstName(String firstName);

	/**
	 * Retrieve a Patient by his id
	 * 
	 * @param id - int
	 * @return Patient
	 * @throws Exception
	 */
	Patient getPatientById(int id) throws PatientNotFoundException;

	/**
	 * Save a Patient
	 * 
	 * @param patient - Patient
	 * @return Patient
	 */
	Patient savePatient(Patient patient);

	/**
	 * Update a Patient
	 * 
	 * @param patient - Patient
	 * @return Patient
	 * @throws Exception
	 */
	Patient updatePatient(Patient patient) throws PatientNotFoundException;

	/**
	 * Delete a Patient by his id
	 * 
	 * @param id - Integer
	 * @return Patient
	 * @throws Exception
	 */
	Patient deletePatientById(int id) throws PatientNotFoundException;
}
