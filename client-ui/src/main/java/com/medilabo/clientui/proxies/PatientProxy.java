package com.medilabo.clientui.proxies;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.medilabo.clientui.beans.PatientBean;

/**
 * 
 * Patient proxy interface to communicate to "patient-ms"(Microservice) with
 * Feign
 * 
 */
@FeignClient(name = "patient-ms", url = "localhost:8080")
public interface PatientProxy {

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

}
