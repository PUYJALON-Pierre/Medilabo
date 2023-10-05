package com.medilabo.patientms.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.service.IPatientService;

@RestController
@RequestMapping("/patient")
public class PatientController {

	final static Logger LOGGER = LogManager.getLogger(PatientController.class);

	private IPatientService iPatientService;

	public PatientController(IPatientService iPatientService) {
		super();
		this.iPatientService = iPatientService;
	}

	@GetMapping("/allPatients")
	public ResponseEntity<List<Patient>> getPatients() {
		LOGGER.debug("GetMapping of all patients");

		List<Patient> patients = iPatientService.getAllPatients();

		if (patients == null) {
			LOGGER.error("Error during recuperation of patients");
			return new ResponseEntity<>(patients, HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Patients list founded");
			return new ResponseEntity<>(patients, HttpStatus.FOUND);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") Integer id) throws Exception {
		LOGGER.debug("Getting request for finding patient with id:{}", id);
		Patient searchPatient = iPatientService.getPatientById(id);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patient with id:{}", id);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patient with id {} founded", id);
		return new ResponseEntity<>(searchPatient, HttpStatus.FOUND);
	}

	@GetMapping("/lastname/{lastname}")
	public ResponseEntity<List<Patient>> getPatientByLastname(@PathVariable("lastname") String lastName) {
		LOGGER.debug("Getting request for finding patient with lastname:{}", lastName);
		List<Patient> searchPatient = iPatientService.getPatientByLastName(lastName);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patients with lastName:{}", lastName);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patients with lastname {} founded", lastName);
		return new ResponseEntity<>(searchPatient, HttpStatus.FOUND);
	}

	@GetMapping("/firstname/{firstname}")
	public ResponseEntity<List<Patient>> getPatientByFirstname(@PathVariable("firstname") String firstname) {
		LOGGER.debug("Getting request for finding patient with firstName:{}", firstname);
		List<Patient> searchPatient = iPatientService.getPatientByFirstName(firstname);
		if (searchPatient == null) {
			LOGGER.error("Error during recuperation of patients with firstname:{}", firstname);
			return new ResponseEntity<>(searchPatient, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Patients with firstname {} founded", firstname);
		return new ResponseEntity<>(searchPatient, HttpStatus.FOUND);
	}


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

	@PutMapping
	public ResponseEntity<Patient> updatePatient(@RequestBody Patient patient) throws Exception {

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

	@DeleteMapping("/{id}")
	public ResponseEntity<Patient> deletePatientWithId(@PathVariable("id") Integer id) throws Exception {

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
