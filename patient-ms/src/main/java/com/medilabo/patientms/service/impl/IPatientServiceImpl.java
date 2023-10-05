package com.medilabo.patientms.service.impl;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.repository.PatientRepository;
import com.medilabo.patientms.service.IPatientService;

import org.springframework.stereotype.Service;

@Service
public class IPatientServiceImpl implements IPatientService{

	final static Logger LOGGER = LogManager.getLogger(IPatientServiceImpl.class);

	PatientRepository patientRepository;

	public IPatientServiceImpl(PatientRepository patientRepository) {
		super();
		this.patientRepository = patientRepository;
	}

	@Override
	public List<Patient> getAllPatients() {
		LOGGER.debug("Start finding all patients");
		LOGGER.info("Getting all patients ");
		List<Patient> patients = patientRepository.findAll();
		if (patients.isEmpty()) {
			LOGGER.error("No patients founded");
		}
		return patients;
	}

	@Override
	public List<Patient> getPatientByLastName(String lastName) {
		LOGGER.debug("Starting finding patient by lastName");
		LOGGER.info("Searching patients with lastname: {} ", lastName);
		List<Patient> patientsByLastName = patientRepository.findByLastname(lastName);
		if (patientsByLastName.isEmpty()) {
			LOGGER.error("No patients founded with lastname : {}", lastName);
		}
		return patientsByLastName;
	}

	@Override
	public List<Patient> getPatientByFirstName(String firstname) {
		LOGGER.debug("Starting finding patient by firstName");
		LOGGER.info("Searching patients with firstname: {} ", firstname);
		List<Patient> patientsByFirstName = patientRepository.findByFirstname(firstname);
		if (patientsByFirstName.isEmpty()) {
			LOGGER.error("No patients founded with firstname : {}", firstname);
		}
		return patientsByFirstName;
	}

	@Override
	public Patient getPatientById(int id) throws Exception {
		LOGGER.debug("Finding patient with id : {}", id);

		if (patientRepository.findById(id).isPresent()) {
			LOGGER.info("Founded patient with id : {}", id);
			return patientRepository.findById(id).get();
		} else {
			LOGGER.error("No patient founded with id : {}", id);
			throw new Exception("Patient cannot be founded with this id");
		}

	}

	@Override
	public Patient savePatient(Patient patient) {
		LOGGER.debug("Saving patient");
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) throws Exception {

		LOGGER.debug("Updating patient with id : {}", patient.getId());
		// Checking if patient already exist before updating
		if (patientRepository.findById(patient.getId()).isPresent()) {
			LOGGER.info("Patient with id : {} updated", patient.getId());
			return patientRepository.save(patient);
		} else {
			LOGGER.error("This patient cannot be updated because not founded");
			throw new Exception("Patient to update not founded");
		}
	}

	@Override
	public Patient deletePatientById(int id) throws Exception {
		LOGGER.debug("Deleting patient with id : {}", id);
		if (getPatientById(id) != null) {
			Patient patientToDelete = getPatientById(id);
			patientRepository.deleteById(id);
			return patientToDelete;
		} else {
			LOGGER.error("This patient cannot be deleted because not founded");
			throw new Exception("Patient to delete not founded");
		}

	}

	
}
