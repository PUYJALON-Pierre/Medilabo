package com.medilabo.doctornotems.service.impl;

import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.medilabo.doctornotems.model.DoctorNote;
import com.medilabo.doctornotems.repository.DoctorNoteRepository;
import com.medilabo.doctornotems.service.IDoctorNoteService;

@Service
public class DoctorNoteServiceImpl implements IDoctorNoteService {

	final static Logger LOGGER = LogManager.getLogger(DoctorNoteServiceImpl.class);

	 DoctorNoteRepository doctorNoteRepository;

	public DoctorNoteServiceImpl(DoctorNoteRepository doctorNoteRepository) {
		super();
		this.doctorNoteRepository = doctorNoteRepository;
	}

	@Override
	public List<DoctorNote> getAllDoctorNotes() {
		LOGGER.debug("Start finding all doctor notes");
		LOGGER.info("Getting all doctor notes ");
		List<DoctorNote> notes = doctorNoteRepository.findAll();
		if (notes.isEmpty()) {
			LOGGER.error("No doctor notes founded");
		}
		return notes;
	}

	@Override
	public List<DoctorNote> getAllDoctorNotesByPatient(int id) {
		LOGGER.debug("Start finding all doctor notes for patient with id : {}", id);
		LOGGER.info("Getting all doctor notes for patient with id : {}", id);
		List<DoctorNote> notes = doctorNoteRepository.findAllByPatientId(id);

		if (notes.isEmpty() || notes.equals(null)) {
			LOGGER.error("No doctor notes foundedfor patient with id : {}", id);
		}
		return notes;

	}

	@Override
	public DoctorNote getDoctorNoteById(String id) throws Exception {
		LOGGER.debug("Finding doctor note with id : {}", id);

		if (doctorNoteRepository.findById(id).isPresent()) {
			LOGGER.info("Founded doctor note with id : {}", id);
			return doctorNoteRepository.findById(id).get();
		} else {
			LOGGER.error("No doctor note founded with id : {}", id);
			throw new Exception("Doctor note cannot be founded with this id");
		}
	}

	@Override
	public DoctorNote saveDoctorNote(DoctorNote doctorNote) {
		LOGGER.debug("Saving doctor note");
		return doctorNoteRepository.insert(doctorNote);
	}

	@Override
	public DoctorNote updateDoctorNote(DoctorNote doctorNote) throws Exception {
		LOGGER.debug("Updating doctor note with id : {}", doctorNote.getNoteId());
		// Checking if doctor note already exist before updating
		if (doctorNoteRepository.findById(doctorNote.getNoteId()).isPresent()) {
			LOGGER.info("Doctor note with id : {} updated", doctorNote.getNoteId());
			return doctorNoteRepository.save(doctorNote);
		} else {
			LOGGER.error("This doctor note cannot be updated because not founded");
			throw new Exception("Doctor note to update not founded");
		}
	}

	@Override
	public DoctorNote deleteDoctorNote(String id) throws Exception {
		LOGGER.debug("Deleting doctor note with id : {}", id);

		if (getDoctorNoteById(id) != null) {
			doctorNoteRepository.deleteById(id);
			return doctorNoteRepository.findById(id).get();
		} else {
			LOGGER.error("This Doctor note cannot be deleted because not founded");
			throw new Exception("Doctor note to delete not founded");

		}
	}

	
	
}
