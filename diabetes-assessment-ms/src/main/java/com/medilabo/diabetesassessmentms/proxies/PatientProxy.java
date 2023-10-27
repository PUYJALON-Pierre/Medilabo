package com.medilabo.diabetesassessmentms.proxies;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.medilabo.diabetesassessmentms.beans.PatientBean;


@FeignClient(name = "patient-ms", url = "localhost:8080")
public interface PatientProxy {

	
	/**
	 * Get Patient by his id from patient-ms
	 * 
	 * @param id - Integer
	 * @return PatientBean
	 */
	@GetMapping("/patient/{id}")
	PatientBean getPatientById(@PathVariable("id") Integer id);
}
