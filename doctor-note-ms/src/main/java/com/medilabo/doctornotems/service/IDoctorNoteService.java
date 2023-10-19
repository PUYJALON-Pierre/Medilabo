package com.medilabo.doctornotems.service;

import java.util.List;

import com.medilabo.doctornotems.model.DoctorNote;

/**
 * Interface for services operations about DocotrNote in doctor-note-ms
 * (Médilabo)
 *
 */
public interface IDoctorNoteService {

	/**
	 * Retrieve a list of all DoctorNotes in database
	 * 
	 * @return List of DoctorNote
	 */
	List<DoctorNote> getAllDoctorNotes();

	/**
	 * Retrieve a list of DoctorNote for a patient from patient id
	 * 
	 * @param id - int
	 * @return List of DoctorNote
	 */
	List<DoctorNote> getAllDoctorNotesByPatient(int id);

	/**
	 * Retrieve a DoctorNote by his id
	 * 
	 * @param id - String
	 * @return DoctorNote
	 * @throws Exception
	 */
	DoctorNote getDoctorNoteById(String id) throws Exception;

	/**
	 * Save a DoctorNote
	 * 
	 * @param doctorNote - DoctorNote
	 * @return DoctorNote
	 */
	DoctorNote saveDoctorNote(DoctorNote doctorNote);

	/**
	 * Update an existing DoctorNote
	 * 
	 * @param doctorNote - DoctorNote
	 * @return DoctorNote
	 * @throws Exception
	 */
	DoctorNote updateDoctorNote(DoctorNote doctorNote) throws Exception;

	/**
	 * Delete a DoctorNote by her id
	 * 
	 * @param id - String
	 * @return DoctorNote
	 * @throws Exception
	 */
	DoctorNote deleteDoctorNote(String id) throws Exception;

}
