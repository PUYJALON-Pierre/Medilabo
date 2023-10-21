package com.medilabo.doctornotems.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medilabo.doctornotems.model.DoctorNote;
import com.medilabo.doctornotems.service.IDoctorNoteService;


import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * Controller CRUD class for DoctorNote in doctor-note-ms (Médilabo)
 *
 */
@RestController
@RequestMapping("/doctorNote")
public class DoctorNoteController {

	
	
	
	final static Logger LOGGER = LogManager.getLogger(DoctorNoteController.class);

	private IDoctorNoteService iDoctorNoteService;

	public DoctorNoteController(IDoctorNoteService iDoctorNoteService) {
		super();
		this.iDoctorNoteService = iDoctorNoteService;
	}

	/**
	 * Request to get all doctor's notes for a patient by her patientId
	 * 
	 * @param patientId - int
	 * @return ResponseEntity(List of Patient)
	 */
	@GetMapping("patient/{patientId}")
	public ResponseEntity<List<DoctorNote>> getAllDoctorNotesForPatient(@PathVariable("patientId") int patientId) {
		LOGGER.debug("GetMapping of all doctor notes for patient {}", patientId);

		List<DoctorNote> notes = iDoctorNoteService.getAllDoctorNotesByPatient(patientId);

		if (notes == null) {
			LOGGER.error("Error during recuperation of doctor notes for patient{}", patientId);
			return new ResponseEntity<>(notes, HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Doctor notes for patient {} founded", patientId);
			return new ResponseEntity<>(notes, HttpStatus.OK);
		}
	}

	/**
	 * Request to get DoctorNote by her id
	 * 
	 * @param noteId - String
	 * @return ResponseEntity (DoctorNote)
	 * @throws Exception
	 */
	@GetMapping("note/{noteId}")
	public ResponseEntity<DoctorNote> getDoctorNoteByNoteId(@PathVariable("noteId") String noteId) throws Exception {
		LOGGER.debug("Getting request to find note with id:{}", noteId);
		DoctorNote note = iDoctorNoteService.getDoctorNoteById(noteId);
		if (note == null) {
			LOGGER.error("Error during recuperation of doctor notes with id {}", noteId);
			return new ResponseEntity<>(note, HttpStatus.NOT_FOUND);
		}
		LOGGER.info("Doctor note with id {} founded", noteId);
		return new ResponseEntity<>(note, HttpStatus.OK);
	}

/**
 * Request to add a new doctor note
 * 
 * @param doctorNote - DoctorNote
 * @return ResponseEntity (DoctorNote)
 */
	@PostMapping
	public ResponseEntity<DoctorNote> addDoctorNote(@RequestBody DoctorNote doctorNote) {

		LOGGER.debug("PostMapping doctor note {} ", doctorNote.getNoteId());
		DoctorNote doctorNoteToAdd = iDoctorNoteService.saveDoctorNote(doctorNote);

		if (doctorNoteToAdd == null) {
			LOGGER.error("Error during adding doctor note : {}", doctorNote.getNoteId());
			return new ResponseEntity<>(doctorNoteToAdd, HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Doctor note {} completed", doctorNote.getNoteId());
			return new ResponseEntity<>(doctorNoteToAdd, HttpStatus.CREATED);
		}
	}

	/**
	 * Request to update a new doctor note
	 * 
	 * @param doctorNote - DoctorNote
	 * @return ResponseEntity (DoctorNote)
	 * @throws Exception
	 */
	@PutMapping
	public ResponseEntity<DoctorNote> updateDoctorNote(@RequestBody DoctorNote doctorNote) throws Exception {
		LOGGER.debug("PutMapping doctor note {} ", doctorNote.getNoteId());
		DoctorNote doctorNoteToUpdate = iDoctorNoteService.updateDoctorNote(doctorNote);

		if (doctorNoteToUpdate == null) {
			LOGGER.error("Error during updating doctor note : {}", doctorNote.getNoteId());
			return new ResponseEntity<>(doctorNoteToUpdate, HttpStatus.BAD_REQUEST);
		} else {
			LOGGER.info("Update of patient completed : {}", doctorNote.getNoteId());
			return new ResponseEntity<>(doctorNoteToUpdate, HttpStatus.OK);
		}

	}

	/**
	 * Request to delete an existing doctor note by her id
	 * 
	 * @param noteId - String
	 * @return ResponseEntity (DoctorNote)
	 * @throws Exception
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<DoctorNote> deleteDoctorNoteWithId(@PathVariable("id") String noteId) throws Exception {

		LOGGER.debug("DeleteMapping doctor note with id {} ", noteId);

		DoctorNote doctorNoteToDelete = iDoctorNoteService.deleteDoctorNote(noteId);
		if (doctorNoteToDelete == null) {
			LOGGER.error("Error during deleting doctor note with id : {}, not founded", noteId);
			return new ResponseEntity<>(doctorNoteToDelete, HttpStatus.NOT_FOUND);
		} else {
			LOGGER.info("Doctor note with id : {}, deleting successfully", noteId);
			return new ResponseEntity<>(doctorNoteToDelete, HttpStatus.OK);
		}

	}
}
