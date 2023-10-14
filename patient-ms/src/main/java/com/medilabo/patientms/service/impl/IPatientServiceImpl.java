package com.medilabo.patientms.service.impl;

import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.medilabo.patientms.exception.PatientNotFoundException;
import com.medilabo.patientms.model.Patient;
import com.medilabo.patientms.repository.PatientRepository;
import com.medilabo.patientms.service.IPatientService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
		LOGGER.debug("Starting finding patient by last name");
		LOGGER.info("Searching patients with last name: {} ", lastName);
		List<Patient> patientsByLastName = patientRepository.findByLastName(lastName);
		if (patientsByLastName.isEmpty()) {
			LOGGER.error("No patients founded with last name : {}", lastName);
		}
		return patientsByLastName;
	}

	@Override
	public List<Patient> getPatientByFirstName(String firstName) {
		LOGGER.debug("Starting finding patient by firstName");
		LOGGER.info("Searching patients with firstname: {} ", firstName);
		List<Patient> patientsByFirstName = patientRepository.findByFirstName(firstName);
		if (patientsByFirstName.isEmpty()) {
			LOGGER.error("No patients founded with first name : {}", firstName);
		}
		return patientsByFirstName;
	}

	
	@Override
	public List<Patient> getPatientByLastNameOrFirstName(String keyword) {
		LOGGER.debug("Starting finding patients by firstName or lastName");
		LOGGER.info("Searching patients with keyword: {} ", keyword);
		List<Patient> patientsByKeyword = patientRepository.findByKeywordContaining(keyword);
		if (patientsByKeyword.isEmpty()) {
			LOGGER.error("No patients founded with keyword : {}", keyword);
		}
		return patientsByKeyword;
	}
	
	
	
	@Override
	public Patient getPatientById(int id) throws PatientNotFoundException  {
		LOGGER.debug("Finding patient with id : {}", id);

		if (patientRepository.findById(id).isPresent()) {
			LOGGER.info("Founded patient with id : {}", id);
			return patientRepository.findById(id).get();
		} else {
			LOGGER.error("No patient founded with id : {}", id);
			throw new PatientNotFoundException("Patient cannot be founded with this id");
		}

	}

	@Override
	public Patient savePatient(Patient patient) {
		LOGGER.debug("Saving patient");
		return patientRepository.save(patient);
	}

	@Override
	public Patient updatePatient(Patient patient) throws PatientNotFoundException {

		LOGGER.debug("Updating patient with id : {}", patient.getId());
		// Checking if patient already exist before updating
		if (patientRepository.findById(patient.getId()).isPresent()) {
			LOGGER.info("Patient with id : {} updated", patient.getId());
			return patientRepository.save(patient);
		} else {
			LOGGER.error("This patient cannot be updated because not founded");
			throw new PatientNotFoundException("Patient to update not founded");
		}
	}

	@Override
	public Patient deletePatientById(int id) throws PatientNotFoundException {
		LOGGER.debug("Deleting patient with id : {}", id);
		if (getPatientById(id) != null) {
			Patient patientToDelete = getPatientById(id);
			patientRepository.deleteById(id);
			return patientToDelete;
		} else {
			LOGGER.error("This patient cannot be deleted because not founded");
			throw new PatientNotFoundException("Patient to delete not founded");
		}

	}



	
}
