package com.medilabo.doctornotems.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.medilabo.doctornotems.model.DoctorNote;

/**
 * DoctorNote repository interface of doctor-note-ms (Médilabo)
 */
@Repository
public interface DoctorNoteRepository extends MongoRepository<DoctorNote, String>{

	public List<DoctorNote> findAllByPatientId (int patientId);
	
	
}
