package com.medilabo.clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.medilabo.clientui.beans.DoctorNoteBean;
import com.medilabo.clientui.beans.PatientBean;
import com.medilabo.clientui.constant.RiskLevel;

/**
 * 
 * Gateway proxy interface to communicate with "patient-ms" "doctor-note-ms" and "diabetes-assessment-ms" by
 * Feign through gateway
 * 
 */
@FeignClient(name = "gateway-server")
public interface GatewayProxy {

	
	/**********************************      Microservice patient-ms      **********************************/
	
	/**
	 * Get all Patients from patient-ms
	 * 
	 * @return List of PatientBean
	 */
	@GetMapping("/patient/allPatients")
	List<PatientBean> getPatients();

	/**
	 * Get Patient by his id from patient-ms
	 * 
	 * @param id - Integer
	 * @return PatientBean
	 */
	@GetMapping("/patient/{id}")
	PatientBean getPatientById(@PathVariable("id") Integer id);

	/**
	 * Get Patients from patient-ms, by a keyword in their first name or last name
	 * 
	 * @param keyword - String
	 * @return List of PatientBean
	 */
	@GetMapping("patient/search")
	List<PatientBean> getPagePatientsByKeyword(@RequestParam(name = "keyword", defaultValue = "") String keyword);

	/**
	 * Post a patient in patient-ms database
	 * 
	 * @param patient - PatientBean
	 * @return PatientBean
	 */
	@PostMapping("/patient")
	PatientBean addPatient(@RequestBody PatientBean patient);

	/**
	 * Update a patient in patient-ms database
	 * 
	 * @param patient - PatientBean
	 * @return PatientBean
	 */
	@PutMapping("/patient")
	PatientBean updatePatient(@RequestBody PatientBean patient);

	/**
	 * Delete a patient by his id in patient-ms database
	 * 
	 * @param id - Integer
	 * @return PatientBean
	 */
	@DeleteMapping("/patient/{id}")
	PatientBean deletePatientById(@PathVariable("id") Integer id);

	
	
	
	/**********************************      Microservice doctor-note-ms      **********************************/

	
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

	
	
	
	/**********************************      Microservice diabetes-assessment-ms      **********************************/
	

	/**
	 * Get diabetes risk level of a specific patient by his patient id with diabetes-assessment-ms
	 * 
	 * @param patientId - int
	 * @return RiskLevel - Enumeration
	 */
	@GetMapping("/diabetesAssessment/{id}")
	RiskLevel getRiskLevelDiabeteByPatientId(@PathVariable("id") Integer id);
	
	
	
	
	
}
