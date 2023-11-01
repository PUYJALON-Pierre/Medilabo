package com.medilabo.clientui.proxies;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import com.medilabo.clientui.constant.RiskLevel;

/**
 * 
 * DiabetesAssessmentProxy interface to communicate to "diabetes-assessment-ms"(Microservice) with
 * Feign
 * 
 */
@FeignClient(name = "gateway-server-diabetes-assessment", url = "localhost:9103")
public interface DiabetesAssessmentProxy {


	
	/**
	 * Get diabetes risk level of a specific patient by his patient id with diabetes-assessment-ms
	 * 
	 * @param patientId - int
	 * @return RiskLevel - Enumeration
	 */
	@GetMapping("/diabetesAssessment/{id}")
	RiskLevel getRiskLevelDiabeteByPatientId(@PathVariable("id") Integer id);
	
	
	
	
	
	
	
	
	
	
	
	
	
}
