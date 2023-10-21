package com.medilabo.clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.medilabo.clientui.beans.DoctorNoteBean;
import com.medilabo.clientui.beans.PatientBean;


/**
 * 
 * DoctorNote proxy interface to communicate to "doctor-note-ms"(Microservice) with
 * Feign
 * 
 */
@FeignClient(name = "doctor-note-ms", url = "localhost:8081")
public interface DoctorNoteProxy {


	/**
	 * Get all DoctorNotes of a specific patient from doctor-note-ms
	 * 
	 * @param patientId - int
	 * @return List of DoctorNoteBean
	 */
	@GetMapping("/doctorNote/patient/{patientId}")
	List<DoctorNoteBean> getDoctorNoteByPatientId(@PathVariable("patientId") int patientId);
	
	
	/**
	 * Add a DoctorNote in doctor-note-ms database
	 * 
	 * @param doctorNote - DoctorNoteBean
	 * @return DoctorNoteBean
	 */
	@PostMapping("/doctorNote")
	DoctorNoteBean addDoctorNoteForPatient(@RequestBody DoctorNoteBean doctorNote);
	
	
	/**
	 * Get DoctorNote by his id from doctor-note-ms
	 * 
	 * @param noteId - String
	 * @return DoctorNoteBean
	 */
    @GetMapping("/doctorNote/note/{noteId}")
    DoctorNoteBean getDoctorNoteById(@PathVariable("noteId") String noteId);
    
    
    /**
     * Delete a DoctorNote by his id in doctor-note-ms database
     * 
     * @param noteId - String
     * @return DoctorNoteBean
     */
    @DeleteMapping("/doctorNote/{id}")
    DoctorNoteBean deleteDoctorNoteForPatient(@PathVariable("id") String noteId);

}

