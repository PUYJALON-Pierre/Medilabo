package com.medilabo.diabetesassessmentms.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.diabetesassessmentms.beans.DoctorNoteBean;

/**
 * 
 * DoctorNote proxy interface to communicate with "doctor-note-ms"(Microservice) by
 * Feign
 * 
 */
@FeignClient(name = "doctor-note-ms")
public interface DoctorNoteProxy {

	/**
	 * Get all DoctorNotes of a specific patient from doctor-note-ms
	 * 
	 * @param patientId - int
	 * @return List of DoctorNoteBean
	 */
	@GetMapping("/doctorNote/patient/{patientId}")
	List<DoctorNoteBean> getDoctorNoteByPatientId(@PathVariable("patientId") int patientId);
	

	
}
