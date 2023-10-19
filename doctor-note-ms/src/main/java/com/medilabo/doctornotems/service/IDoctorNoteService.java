package com.medilabo.doctornotems.service;

import java.util.List;

import com.medilabo.doctornotems.model.DoctorNote;

public interface IDoctorNoteService {

	List<DoctorNote> getAllDoctorNotes();

	List<DoctorNote> getAllDoctorNotesByPatient(int id);

	DoctorNote getDoctorNoteById(String id) throws Exception;

	DoctorNote saveDoctorNote(DoctorNote doctorNote);

	DoctorNote updateDoctorNote(DoctorNote doctorNote) throws Exception;

	DoctorNote deleteDoctorNote(String id) throws Exception;

}
