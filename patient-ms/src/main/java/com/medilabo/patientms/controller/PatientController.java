package com.medilabo.patientms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.patientms.exception.PatientNotFoundException;
import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.service.IPatientService;

/**
 * Controller CRUD class for Patient in patient microservice (Médilabo)
 *
 */
@RestController
@RequestMapping("/patient")
@CrossOrigin("http://localhost:8083")
public class PatientController {

	final static Logger LOGGER = LogManager.getLogger(PatientController.class);

	private IPatientService iPatientService;

	public PatientController(IPatientService iPatientService) {
		super();
		this.iPatientService = iPatientService;
	}

	/**
	 * Request to get all patients
	 * 
	 * @return ResponseEntity(List of Patient)
	 */
	@GetMapping("/allPatients")
	public ResponseEntity<List<Patient>> getPatients() {
		LOGGER.debug("GetMapping of all patients");

		List<Patient> patients = iPatientService.getAllPatients();

		if (patients == null) {
			LOGGER.error("Error during recuperation of patients");
			return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Patients list founded");
			return new ResponseEntity<>(patients, HttpStatus.OK);
		}
	}

	/**
	 * Request to get patient by his id
	 * 
	 * @param id - Integer
	 * @return ResponseEntity (Patient)
	 * @throws Exception
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) throws PatientNotFoundException {
		LOGGER.debug("Getting request for finding patient with id:{}", id);
		Patient searchPatient = iPatientService.getPatientById(id);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patient with id:{}", id);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patient with id {} founded", id);
		return new ResponseEntity<>(searchPatient, HttpStatus.OK);
	}

	/**
	 * Request to get patient by his lastName
	 * 
	 * @param lastname - String
	 * @return ResponseEntity (Patient)
	 * @throws Exception
	 */
	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<List<Patient>> getPatientByLastName(@PathVariable("lastname") String lastName) {
		LOGGER.debug("Getting request for finding patient with last name:{}", lastName);
		List<Patient> searchPatient = iPatientService.getPatientByLastName(lastName);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patients with last name:{}", lastName);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patients with last name {} founded", lastName);
		return new ResponseEntity<>(searchPatient, HttpStatus.OK);
	}

	/**
	 * Request to get patient by his firstName
	 * 
	 * @param firstname - String
	 * @return ResponseEntity (Patient)
	 * @throws Exception
	 */
	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<List<Patient>> getPatientByFirstName(@PathVariable("firstname") String firstName) {
		LOGGER.debug("Getting request for finding patient with first name:{}", firstName);
		List<Patient> searchPatient = iPatientService.getPatientByFirstName(firstName);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patients with first name:{}", firstName);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patients with first name {} founded", firstName);
		return new ResponseEntity<>(searchPatient, HttpStatus.OK);
	}

	/**
	 * Request to get patient by keyword matching firstName or lastName
	 * 
	 * @param keyword     - String
	 * @param currentPage - int
	 * @param pageSize    - int
	 * @return ResponseEntity (Page of Patient)
	 */
	@GetMapping("/search/{keyword}")
	public ResponseEntity<List<Patient>> getPagePatientsByKeyword(@RequestParam(name = "keyword", defaultValue = "") String keyword) {
		LOGGER.debug("Getting request for finding patients with keyword:{}", keyword);

		List<Patient> searchPatient = iPatientService.getPatientByLastNameOrFirstName(keyword);

		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patients with keyword:{}", keyword);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patients with keyword founded", keyword);
		return new ResponseEntity<>(searchPatient, HttpStatus.OK);
	}

	/**
	 * Request to add a new patient
	 * 
	 * @param patient - Patient
	 * @return ResponseEntity (Patient)
	 */
	@PostMapping
	public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {

		LOGGER.debug("PostMapping patient {} ", patient);
		Patient patientToAdd = iPatientService.savePatient(patient);

		if (patientToAdd == null) {
			LOGGER.error("Error during adding patient : {}", patient.getId());
			return new ResponseEntity<>(patientToAdd, HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Adding of patient completed : {}", patient.getId());
			return new ResponseEntity<>(patientToAdd, HttpStatus.CREATED);
		}
	}

	/**
	 * Request to update an existing patient
	 * 
	 * @param patient - Patient
	 * @return ResponseEntity (Patient)
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws PatientNotFoundException {

		LOGGER.debug("PutMapping patient {} ", patient.getId());
		Patient patientToUpdate = iPatientService.updatePatient(patient);

		if (patientToUpdate == null) {
			LOGGER.error("Error during updating patient : {}", patient.getId());
			return new ResponseEntity<>(patientToUpdate, HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Updating patient completed : {}", patient.getId());
			return new ResponseEntity<>(patientToUpdate, HttpStatus.OK);
		}
	}

	/**
	 * Request to delete an existing patient by his id
	 * 
	 * @param id - Integer
	 * @return ResponseEntity (Patient)
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> deletePatientWithId(@PathVariable("id") Integer id) throws PatientNotFoundException {

		LOGGER.debug("DeleteMapping for patient with id {} ", id);

		Patient personsToDelete = iPatientService.deletePatientById(id);
		if (personsToDelete == null) {
			LOGGER.error("Error during deleting person with id : {}, not founded", id);
			return new ResponseEntity<>(personsToDelete, HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Person with id : {}, deleting successfully", id);
			return new ResponseEntity<>(personsToDelete, HttpStatus.OK);
		}

	}
}
